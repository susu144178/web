const AGE = 20;
const BIRTH = '971119';

// export : 해당 파일 외부에서 특정 변수 혹은 함수 등을 사용 할 수 있도록 내보내는 역할
//? export 요소; -> 파일의 요소로 import를 할 수 있도록 함
//?                 한 파일 내에서 여러개를 export로 내보낼 수 있음
//?                 import 하는 파일에서 받아올 때
//?                 import { 요소, 요소, ... } from '파일경로';
//?                 로 받을 수 있음
//! export 요소; 로 내보낸 요소는 import 할 때 내보낸 요소의 이름과 동일한 이름으로 받아야 함
export const NAME = '김선우';
export const PHONE = '11111111';

//? export default 요소; -> 파일의 기본 값으로 import를 할 수 있도록 함
//?                         한 파일 내에서 하나만 export default로 내보낼 수 있음
//?                         import 하는 파일에서 받아올 때
//?                         import 이름 from '파일경로';
//?                         로 받을 수 있음
//! export default 요소; 로 내보낸 요소는 import 할 때 내보낸 요소의 이름과 달라도 됨
export default AGE;
// export default BIRTH;

export const PAGES = [
    {
        title: 'Naver',
        viewValue: 'naverSignIn',
    },
    {
        title: 'Kakao',
        viewValue: 'kakaoSignIn',
    },
    {
        title: 'ES6 + Typescript',
        viewValue: 'es6TypeScript',
    },
    {
        title: 'JSX + TSX',
        viewValue: 'jsxTsx'
    }
]