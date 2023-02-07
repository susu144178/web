import React from "react";
import LoginView from "./views/AuthenticationView";
import NavigationBar from "./views/NavigationBar";

export default function App() {
  return (
    <>
      <NavigationBar />
      <LoginView />
    </>
  );
}
