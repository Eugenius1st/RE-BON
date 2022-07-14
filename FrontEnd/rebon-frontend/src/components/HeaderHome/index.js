import '../../styles/header-home.css';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import SearchModal from './SearchModal';
export default function Header() {
  const userName = localStorage.getItem('userName');
  //로그인인지 아닌지에 따라 헤더 상태 다름
  const [isLogin, setIsLogin] = useState(true);
  function Login(e) {
    setIsLogin(true);
  }

  return (
    <div className="header-home-wrapper">
      <header>
        <Link to="/">
          <img src="/image/logo.png" alt="logo" className="logo-home-img" />
        </Link>
        {isLogin ? (
          <div className="header-home-logon">
            <div className="header-home-logon-modal">
              <SearchModal />
            </div>

            <Link to="/mypage" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div className="header-home-logon-icon">
                <img src="/image/user.png" alt="user" />
              </div>
            </Link>
            <span className="header-home-logon-name">{userName}</span>
          </div>
        ) : (
          <div className="header-home-logoff">
            <Link to="/login" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div>로그인</div>
            </Link>
            <Link to="/join" style={{ color: 'inherit', textDecoration: 'none' }}>
              <div> 회원가입</div>
            </Link>
          </div>
        )}
      </header>
    </div>
  );
}
