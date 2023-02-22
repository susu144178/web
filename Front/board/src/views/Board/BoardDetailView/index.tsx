import {
  Avatar,
  Box,
  Card,
  Divider,
  IconButton,
  Menu,
  MenuItem,
  Typography,
} from "@mui/material";
import { MouseEvent, useEffect, useState } from "react";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import MessageOutlinedIcon from "@mui/icons-material/MessageOutlined";
import KeyboardArrowDownOutlinedIcon from "@mui/icons-material/KeyboardArrowDownOutlined";
import KeyboardArrowUpOutlinedIcon from "@mui/icons-material/KeyboardArrowUpOutlined";
import FavoriteOutlinedIcon from "@mui/icons-material/FavoriteOutlined";
import { useNavigate, useParams } from "react-router-dom";
import { BOARD_LIST, LIKE_LIST } from "src/mock";
import { ILikeUser, IPreviewItem } from "src/interfaces";
import { useUserStore } from "src/stores";
import LikeListItem from "src/components/LikeListItem";

export default function BoardDetailView() {
  const [anchorElement, setAnchorElement] = useState<null | HTMLElement>(null);
  const [menuFlag, setMenuFlag] = useState<boolean>(false);
  const [menuOpen, setMenuOpen] = useState<boolean>(false);
  const [board, setBoard] = useState<null | IPreviewItem>(null);
  const [likeStatus, setLikeStatus] = useState<boolean>(false);
  const [openLike, setOpenLike] = useState<boolean>(false);
  const [likeList, setLikeList] = useState<ILikeUser[]>([]);
  const [openComment, setOpenComment] = useState<boolean>(false);

  const { boardNumber } = useParams();
  const navigator = useNavigate();

  const { user } = useUserStore();

  const onMenuClickHandler = (event: MouseEvent<HTMLButtonElement>) => {
    setAnchorElement(event.currentTarget);
    setMenuOpen(true);
  };

  const onMenuCloseHandler = () => {
    setAnchorElement(null);
    setMenuOpen(false);
  };

  useEffect(() => {
    //? boardNumber가 존재하는지 검증
    if (!boardNumber) {
      navigator("/");
      return;
    }
    //? BOARD_LIST에서 boardNumber에 해당하는 board를 가져옴
    const board = BOARD_LIST.find(
      (boardItem) => boardItem.boardNumber === parseInt(boardNumber)
    );
    //? 검색한 결과가 존재하는지 검증
    if (!board) {
      navigator("/");
      return;
    }

    setLikeList(LIKE_LIST);

    const owner = user !== null && user.nickname === board.writerNickname;
    setMenuFlag(owner);

    setBoard(board);
  }, []);

  return (
    <Box sx={{ p: "100px 222px" }}>
      <Box>
        <Box>
          <Typography sx={{ fontSize: "32px", fontWeight: 500 }}>
            {board?.boardTitle}
          </Typography>
          <Box
            sx={{
              mt: "20px",
              display: "flex",
              justifyContent: "space-between",
            }}
          >
            <Box sx={{ display: "flex", alignItems: "center" }}>
              <Avatar
                src={board?.writerProfile}
                sx={{ height: "32px", width: "32px", mr: "8px" }}
              />
              <Typography sx={{ mr: "8px", fontSize: "16px", fontWeight: 500 }}>
                {board?.writerNickname}
              </Typography>
              <Divider
                sx={{ mr: "8px" }}
                orientation="vertical"
                variant="middle"
                flexItem
              />
              <Typography
                sx={{ fontSize: "16px", fontWeight: 400, opacity: 0.4 }}
              >
                {board?.writeDate}
              </Typography>
            </Box>
            {menuFlag && (
              <IconButton onClick={(event) => onMenuClickHandler(event)}>
                <MoreVertIcon />
              </IconButton>
            )}
            <Menu
              anchorEl={anchorElement}
              open={menuOpen}
              onClose={onMenuCloseHandler}
            >
              <MenuItem sx={{ p: "10px 59px", opacity: 0.5 }} onClick={() => navigator(`/board/update/${board?.boardNumber}`)}>수정</MenuItem>
              <Divider />
              <MenuItem sx={{ p: "10px 59px", color: "#ff0000", opacity: 0.5 }}>
                삭제
              </MenuItem>
            </Menu>
          </Box>
        </Box>
        <Divider sx={{ m: "40px 0px" }} />
        <Box>
          <Typography sx={{ fontSize: "18px", fontWeight: 500, opacity: 0.7 }}>
            {board?.boardContent}
          </Typography>
          {board?.img && (
            <Box
              sx={{ width: "100%", mt: "20px" }}
              component="img"
              src={board?.img}
            />
          )}
        </Box>
        <Box sx={{ display: "flex", mt: "20px" }}>
          <Box sx={{ mr: "20px", display: "flex" }}>
            {likeStatus ? (
              <FavoriteOutlinedIcon sx={{ height: "24px", width: "24px", mr: "6px", opacity: 0.7, color: '#ff0000' }}
              onClick={() => setLikeStatus(!likeStatus)} />
            ) : (
              <FavoriteBorderIcon
                sx={{ height: "24px", width: "24px", mr: "6px", opacity: 0.7 }}
                onClick={() => setLikeStatus(!likeStatus)}
              />
            )}
            <Typography
              sx={{
                fontSize: "16px",
                fontWeight: 500,
                opacity: 0.7,
                mr: "6px",
              }}
            >
              좋아요 {board?.likeCount}
            </Typography>
            <IconButton sx={{ height: "24px", width: "24px" }} onClick={() => setOpenLike(!openLike)}>
                { openLike ? (<KeyboardArrowUpOutlinedIcon />) : (<KeyboardArrowDownOutlinedIcon />) }
            </IconButton>
          </Box>
          <Box sx={{ display: "flex" }}>
            <MessageOutlinedIcon
              sx={{ height: "24px", width: "24px", mr: "6px", opacity: 0.7 }}
            />
            <Typography
              sx={{
                fontSize: "16px",
                fontWeight: 500,
                opacity: 0.7,
                mr: "6px",
              }}
            >
              댓글 {board?.commentCount}
            </Typography>
            <IconButton sx={{ height: "24px", width: "24px" }} onClick={() => setOpenComment(!openComment)}>
                { openComment ? (<KeyboardArrowUpOutlinedIcon />) : (<KeyboardArrowDownOutlinedIcon />) }  
            </IconButton>
          </Box>
        </Box>
      </Box>
      { openLike && (<Box sx={{ mt: '20px' }}>
            <Card variant="outlined" sx={{ p: '20px' }}>
              <Typography>좋아요 {board?.likeCount}</Typography>
              <Box sx={{ m: '20px 0px' }}>
                { LIKE_LIST.map((likeUser) => (<LikeListItem likeUser={likeUser}/>)) }
              </Box>
            </Card>
      </Box>) }
      <Box>

      </Box>
    </Box>
  );
}
