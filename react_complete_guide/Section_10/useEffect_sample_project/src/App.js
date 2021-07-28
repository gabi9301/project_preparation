import React, { useState, useEffect } from 'react';

import Login from './components/Login/Login';
import Home from './components/Home/Home';
import MainHeader from './components/MainHeader/MainHeader';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const storedLoggedInInfo = localStorage.getItem('isLoggedIn');

    if(storedLoggedInInfo === '1') {
      setIsLoggedIn(true);
    }

  }, []);

  const loginHandler = (email, password) => {
    // We should of course check email and password
    // But it's just a dummy/ demo anyways
    

    // 로그인 정보가 단순히 React의 state로 설정되어 있을 경우,
    // 애플리케이션이 로드될 때마다 스크립트가 재실행되면서 
    // 해당 정보는 사라진다. 즉, 페이지를 새로고침 할 때마다 로그인 정보가 초기화된다.
    // 이같은 상황을 피하기 위해 브라우저 스토리지에 정보를 저장해보자. (리액트에 독립적인 브라우저의 로컬 스토리지)


    localStorage.setItem('isLoggedIn','1');  //1 =  로그인 O, 0 = 로그인 X

    setIsLoggedIn(true);

  };

  const logoutHandler = () => {
    localStorage.removeItem('isLoggedIn');
    setIsLoggedIn(false);
  };

  return (
    <React.Fragment>
      <MainHeader isAuthenticated={isLoggedIn} onLogout={logoutHandler} />
      <main>
        {!isLoggedIn && <Login onLogin={loginHandler} />}
        {isLoggedIn && <Home onLogout={logoutHandler} />}
      </main>
    </React.Fragment>
  );
}

export default App;
