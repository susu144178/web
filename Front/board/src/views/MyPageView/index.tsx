import { Box } from '@mui/material';

import MypageHead from './MyPageHead';
import MyPageContents from './MyPageContents';

export default function MyPageView() {
  return (
    <Box>
        <MypageHead />
        <MyPageContents />
    </Box>
  )
}
