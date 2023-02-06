import { useState } from "react";
//? Box 컴포넌트 : mui에서 공간을 할당하는 모든 태그를 포함
//? Grid 컴포넌트 : mui에서 공간을 12등분하여 가로 사이즈에 따라 반응형 웹 개발을 지원
//? Typography 컴포넌트 : mui에서 글자를 출력하는 모든 태그를 포함
import {
  Box,
  Card,
  FormControl,
  Grid,
  IconButton,
  Input,
  InputAdornment,
  InputLabel,
  TextField,
  Typography,
} from "@mui/material";

import ContentPasteTwoToneIcon from "@mui/icons-material/ContentPasteTwoTone";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import Visibility from "@mui/icons-material/Visibility";

export default function LoginView() {
    const [showPassword, setShowPassword] = useState<boolean>();
  return (
    <Box sx={{ pr: "120px", pl: "120px" }}>
      <Grid container spacing={2}>
        <Grid item lg={7} sm={12}>
          <Box
            sx={{
              display: "flex",
              height: "100%",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <ContentPasteTwoToneIcon sx={{ fontSize: 40 }} />
            <Typography variant="h4">환영합니다.</Typography>
            <Typography variant="h4">SEON BOARD입니다.</Typography>
          </Box>
        </Grid>
        <Grid item lg={5} sm={12}>
          <Card
            sx={{
              height: "710px",
              mt: "100px",
              mb: "80px",
              pt: "50px",
              pb: "30px",
              pl: "50px",
              pr: "50px",
            }}
          >
            <Typography variant="h5" fontWeight="900">
              로그인
            </Typography>
            <TextField
              sx={{ mt: "40px" }}
              fullWidth
              label="이메일 주소"
              variant="standard"
            />
            <FormControl sx={{ m: 1, width: "25ch" }} variant="standard">
              <InputLabel htmlFor="standard-adornment-password">
                Password
              </InputLabel>
              <Input
                type={showPassword ? "text" : "password"}
                endAdornment={
                  <InputAdornment position="end">
                    <IconButton
                      aria-label="toggle password visibility"
                      onClick={() => setShowPassword(!showPassword)}
                    >
                      {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                }
              />
            </FormControl>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
}
