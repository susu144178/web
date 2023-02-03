import { useState } from "react";
import KakaoSignIn from "./views/KakaoSignIn";
import NaverSignIn from "./views/NaverSignIn";

import './App.css';

export default function App() {
  const [view, setView] = useState<string>("");
  return (
    <div>
      <button onClick={() => setView("naverSignIn")}>
        네이버 회원가입 화면
      </button>
      <button onClick={() => setView("kakaoSignIn")}>
        카카오 로그인 화면
      </button>
      <div>
        {/* {view === "naverSignIn" && <NaverSignIn />} */}
        {/*
        //? 삼항 비교 연산자
        // 조건에 따라서 참일때의 결과 값과 거짓일때의 결과 값을 지정해서
        // 해당하는 경우의 값을 반환
        // 조건 ? 참일 때의 결과 값 : 거짓일 때의 결과 값
        */}
        {/* if(조건) {} else { if(조건) {} else {} } */}
        {/*
          //! <></> : 아무런 의미가 없는 빈 태그        
        */}
        {
          view === 'naverSignIn' ? (<NaverSignIn />) : 
          view === 'kakaoSignIn' ? (<KakaoSignIn />) : (<></>)
        }
      </div>
    </div>
  );
}
