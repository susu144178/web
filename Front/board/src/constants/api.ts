export const authorizationHeader = (accessToken: string) => {
    return { headers: { Authorization:`Bearer ${accessToken}` } }
}

const HOST = 'http://localhost:4040/';

export const SIGN_UP_URL = `${HOST}auth/sign-up`;
export const SIGN_IN_URL = `${HOST}auth/sign-in`;

export const GET_USER_URL = `${HOST}api/user/`;

export const GET_LIST_URL = `${HOST}api/board/list`;
export const GET_SEARCH_LIST_URL = (content: string) => `${HOST}api/board/search-list/${content}`;