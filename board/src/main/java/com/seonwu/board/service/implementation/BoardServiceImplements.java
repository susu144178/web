package com.seonwu.board.service.implementation;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seonwu.board.common.constant.ResponseMessage;
import com.seonwu.board.dto.request.board.LikeDto;
import com.seonwu.board.dto.request.board.PatchBoardDto;
import com.seonwu.board.dto.request.board.PostBoardDto;
import com.seonwu.board.dto.request.board.PostCommentDto;
import com.seonwu.board.dto.response.ResponseDto;
import com.seonwu.board.dto.response.board.DeleteBoardResponseDto;
import com.seonwu.board.dto.response.board.GetBoardResponseDto;
import com.seonwu.board.dto.response.board.GetListResponseDto;
import com.seonwu.board.dto.response.board.GetMyListResponseDto;
import com.seonwu.board.dto.response.board.GetSearchListResponseDto;
import com.seonwu.board.dto.response.board.GetTop15RelatedSearchWordResponseDto;
import com.seonwu.board.dto.response.board.GetTop15SearchWordResponseDto;
import com.seonwu.board.dto.response.board.GetTop3ListResponseDto;
import com.seonwu.board.dto.response.board.LikeResponseDto;
import com.seonwu.board.dto.response.board.PatchBoardResponseDto;
import com.seonwu.board.dto.response.board.PostBoardResponseDto;
import com.seonwu.board.dto.response.board.PostCommentResponseDto;
import com.seonwu.board.entity.BoardEntity;
import com.seonwu.board.entity.CommentEntity;
import com.seonwu.board.entity.LikyEntity;
import com.seonwu.board.entity.RelatedSearchWordEntity;
import com.seonwu.board.entity.SearchWordLogEntity;
import com.seonwu.board.entity.UserEntity;
import com.seonwu.board.entity.resultSet.RelatedSearchWordResultSet;
import com.seonwu.board.entity.resultSet.SearchWordResultSet;
import com.seonwu.board.repository.BoardRepository;
import com.seonwu.board.repository.CommentRepository;
import com.seonwu.board.repository.LikyRepository;
import com.seonwu.board.repository.RelatedSearchWordRepository;
import com.seonwu.board.repository.SearchWordLogRepository;
import com.seonwu.board.repository.UserRepository;
import com.seonwu.board.service.BoardService;

@Service
public class BoardServiceImplements implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikyRepository likyRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SearchWordLogRepository searchWordLogRepository;

    @Autowired
    private RelatedSearchWordRepository relatedSearchWordRepository;

    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto dto) {

        PostBoardResponseDto data = null;

        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = new BoardEntity(userEntity, dto);
            boardRepository.save(boardEntity);

            data = new PostBoardResponseDto(boardEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetListResponseDto>> getList() {

        List<GetListResponseDto> data = null;

        try {

            List<BoardEntity> boardEntityList = boardRepository.findByOrderByBoardWriteDatetimeDesc();
            data = GetListResponseDto.copyList(boardEntityList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetMyListResponseDto>> getMyList(String email) {

        List<GetMyListResponseDto> data = null;

        try {

            List<BoardEntity> boardList = boardRepository.findByWriterEmailOrderByBoardWriteDatetimeDesc(email);

            data = GetMyListResponseDto.copyList(boardList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<GetBoardResponseDto> getBoard(int boardNumber) {

        GetBoardResponseDto data = null;

        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            List<LikyEntity> likyList = likyRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

            data = new GetBoardResponseDto(boardEntity, commentList, likyList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PatchBoardResponseDto> patchBoard(String email, PatchBoardDto dto) {

        PatchBoardResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = email.equals(boardEntity.getWriterEmail());
            if (!isEqualWriter) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            boardEntity.patch(dto);
            boardRepository.save(boardEntity);

            List<LikyEntity> likyList = likyRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

            data = new PatchBoardResponseDto(boardEntity, commentList, likyList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteBoardResponseDto> deleteBoard(String email, int boardNumber) {

        DeleteBoardResponseDto data = null;

        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = email.equals(boardEntity.getWriterEmail());
            if (!isEqualWriter) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            commentRepository.deleteByBoardNumber(boardNumber);
            likyRepository.deleteByBoardNumber(boardNumber);

            boardRepository.delete(boardEntity);
            data = new DeleteBoardResponseDto(true);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<LikeResponseDto> like(String email, LikeDto dto) {

        LikeResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            LikyEntity likyEntity = likyRepository.findByUserEmailAndBoardNumber(email, boardNumber);
            if (likyEntity == null) {
                likyEntity = new LikyEntity(userEntity, boardNumber);
                likyRepository.save(likyEntity);
                boardEntity.increaseLikeCount();
            }
            else {
                likyRepository.delete(likyEntity);
                boardEntity.decreaseLikeCount();
            }
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<LikyEntity> likeList = likyRepository.findByBoardNumber(boardNumber);

            data = new LikeResponseDto(boardEntity, commentList, likeList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    public ResponseDto<PostCommentResponseDto> postComment(String email, PostCommentDto dto) {

        PostCommentResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            CommentEntity commentEntity = new CommentEntity(userEntity, dto);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<LikyEntity> likeList = likyRepository.findByBoardNumber(boardNumber);

            data = new PostCommentResponseDto(boardEntity, commentList, likeList);
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetSearchListResponseDto>> getSearchList(String searchWord, String previousSearchWord) {

        List<GetSearchListResponseDto> data = null;

        try {

            SearchWordLogEntity searchWordLogEntity = new SearchWordLogEntity(searchWord);
            searchWordLogRepository.save(searchWordLogEntity);

            if (previousSearchWord != null && !previousSearchWord.isBlank()) {
                RelatedSearchWordEntity relatedSearchWordEntity = new RelatedSearchWordEntity(searchWord, previousSearchWord);
                relatedSearchWordRepository.save(relatedSearchWordEntity);
            }

            List<BoardEntity> boardList = boardRepository.findByBoardTitleContainsOrBoardContentContainsOrderByBoardWriteDatetimeDesc(searchWord, previousSearchWord);

            data = GetSearchListResponseDto.copyList(boardList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
    public ResponseDto<GetTop15SearchWordResponseDto> getTop15SearchWord() {

        GetTop15SearchWordResponseDto data = null;

        try {

            List<SearchWordResultSet> searchWordList = searchWordLogRepository.findTop15();
            data = GetTop15SearchWordResponseDto.copyList(searchWordList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<GetTop15RelatedSearchWordResponseDto> getTop15RelatedSearchWord(String searchWord) {

        GetTop15RelatedSearchWordResponseDto data = null;

        try {

            List<RelatedSearchWordResultSet> relatedSearchWordList = relatedSearchWordRepository.findTop15(searchWord);
            data = GetTop15RelatedSearchWordResponseDto.copyList(relatedSearchWordList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetTop3ListResponseDto>> getTop3List() {
        
        List<GetTop3ListResponseDto> data = null;
        Date aWeekAgoDate = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String aWeekAgo = simpleDateFormat.format(aWeekAgoDate);

        try {

            List<BoardEntity> boardList = boardRepository.findTop3ByBoardWriteDatetimeGreaterThanOrderByLikeCountDesc(aWeekAgo);
            data = GetTop3ListResponseDto.copyList(boardList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
