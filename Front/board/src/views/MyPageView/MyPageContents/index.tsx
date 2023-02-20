import { useEffect } from 'react'
import { Card, Grid, Box, Typography, Pagination, CardActionArea } from '@mui/material';
import CreateIcon from '@mui/icons-material/Create';

import { usePagingHook } from 'src/hooks';
import { BOARD_LIST } from 'src/mock';
import { useUserStore } from 'src/stores';

export default function MyPageContents() {

    const { boardList, viewList, pageNumber, setBoardList, onPageHandler, COUNT } = usePagingHook();
    const { user } = useUserStore();

    useEffect(() => {
        const tmp = BOARD_LIST.filter((board) => board.writerNickname === user?.nickname);
        setBoardList(tmp);
    }, [])

  return (
    <Box sx={{ p: '40px 120px', backgroundColor: 'rgba(0, 0, 0, 0.05)' }}>
        <Box>
            <Typography sx={{ fontSize: '24px', fontWeight: 500 }}>내 게시물 10</Typography>
        </Box>
        <Box sx={{ mt: '20px', mb: '80px' }}>
            <Grid container spacing={3}>
                <Grid item sm={12} md={8}></Grid>
                <Grid item sm={12} md={4}>
                    <Card variant='outlined'>
                        <CardActionArea sx={{ p: '25px 0px', display: 'flex', justifyContent: 'center' }}>
                            <CreateIcon sx={{ mr: '6px' }}/>
                            <Typography sx={{ fontSize: '18px', fontWeight: 500 }}>글쓰기</Typography>
                        </CardActionArea>
                    </Card>
                </Grid>
            </Grid>
        </Box>
        <Box sx={{ display: 'flex', justifyContent: 'center' }}>
            <Pagination />
        </Box>
    </Box>
  )
}
