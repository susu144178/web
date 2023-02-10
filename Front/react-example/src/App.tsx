import { useState } from "react";
import KakaoSignIn from "./views/KakaoSignIn";
import NaverSignIn from "./views/NaverSignIn";
import MenuAppBar from "./components/MenuAppBar";

import { Link } from "react-router-dom";

import './App.css';
import Es6Typescript from "./views/Es6TypeScript";
import JsxTsx from "./views/JsxTsx";
import { VIEW } from "./enums";
import Hook from "./views/Hook";

export default function App() {
  const [view, setView] = useState<VIEW>(VIEW.NAVER);
  return (
    <div>
      <MenuAppBar setView={setView} />
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
          view === VIEW.NAVER ? (<NaverSignIn />) : 
          view === VIEW.KAKAO ? (<KakaoSignIn />) :
          view === VIEW.TYPESCRIPT ? (<Es6Typescript />) :
          view === VIEW.TSX ? (<JsxTsx />) :
          view === VIEW.HOOK ? (<Hook/>) :
          view === VIEW.MUI ? (<></>) : (<></>)
        }
      </div>
    </div>
  );
}
