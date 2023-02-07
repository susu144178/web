import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import { Box, Button, FormControl, IconButton, Input, InputAdornment, InputLabel, TextField, Typography } from "@mui/material";
import { Dispatch, SetStateAction, useState } from "react";

function FirstPage() {

    const [showPassword, setShowPassword] = useState<boolean>(false);
    const [showPasswordCheck, setShowPasswordCheck] = useState<boolean>(false);

  return (
    <Box>
        <TextField sx={{ mt: '40px' }} fullWidth label='이메일 주소*' variant="standard" />
        <FormControl fullWidth variant="standard" sx={{ mt: "40px" }}>
          <InputLabel>비밀번호*</InputLabel>
          <Input
            type={showPassword ? "text" : "password"}
            endAdornment={
              <InputAdornment position="end">
                <IconButton onClick={() => setShowPassword(!showPassword)}>
                  {showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
            }
          />
        </FormControl>
        <FormControl fullWidth variant="standard" sx={{ mt: "40px" }}>
          <InputLabel>비밀번호 확인*</InputLabel>
          <Input
            type={showPasswordCheck ? "text" : "password"}
            endAdornment={
              <InputAdornment position="end">
                <IconButton onClick={() => setShowPasswordCheck(!showPasswordCheck)}>
                  {showPasswordCheck ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
            }
          />
        </FormControl>
    </Box>
  )
}

function SecondPage() {
  return (
    <Box>두번째 페이지</Box>
  )
}

interface Props {
  setLoginView: Dispatch<SetStateAction<boolean>>;
}

export default function SignUpCardView({ setLoginView }: Props) {
  const [page, setPage] = useState<number>(1);

  const onNextButtonHandler = () => {
    //todo: 이메일 / 비밀번호 / 비밀번호 확인 검증
    //todo: 검증이 실패하면 return
    //todo: 검증이 성공하면 page 변경
    setPage(2);
  };

  return (
    <Box
      display="flex"
      sx={{
        height: "100%",
        flexDirection: "column",
        justifyContent: "space-between",
      }}
    >
      <Box>
        <Box display="flex" sx={{ justifyContent: "space-between" }}>
          <Typography variant="h5" fontWeight="900">
            회원가입
          </Typography>
          <Typography variant="h5" fontWeight="900">
            {page}/2
          </Typography>
        </Box>
        { page === 1 ? (<FirstPage />) : (<SecondPage />)}
      </Box>
      <Box>
        {page === 1 && (
          <Button
            fullWidth
            variant="contained"
            size="large"
            sx={{ mb: "20px" }}
            onClick={onNextButtonHandler}
          >
            다음 단계
          </Button>
        )}
        {page === 2 && (
          <Button
            fullWidth
            variant="contained"
            size="large"
            sx={{ mb: "20px" }}
            onClick={() => setPage(1)}
          >
            회원가입
          </Button>
        )}
        <Typography textAlign="center">
          이미 계정이 있으신가요?
          <Typography
            component="span"
            fontWeight={900}
            onClick={() => setLoginView(true)}
          >
            {" "}
            로그인
          </Typography>
        </Typography>
      </Box>
    </Box>
  );
}
