import React, { useEffect, useState } from "react";
import Cookies from "js-cookie";
import '../style.css';

function MainPage() {
    const handleMenuClick = () => {
        window.location.href = '/menu1'; // 메뉴 페이지로 이동
    };
    const [userId, setUserId] = useState(null);

    useEffect(() => {
        // 브라우저 쿠키에서 userId 읽기
        const userIdFromCookie = Cookies.get("userId");
        setUserId(userIdFromCookie); // 로그인된 사용자 아이디 설정
    }, []);

    return (
        <div>
            <header>
                {/* 로고 */}
                <img src="/png/logo.png" alt="logo" className="logo"/>
                <h1>TRAVEL KIT</h1>
                <img
                    src="/png/menu.png"
                    alt="menu icon"
                    className="menu"
                    onClick={handleMenuClick}
                />
            </header>
            {/* 광고 이미지 */}
            <img src="/png/adv.png" alt="advertisement" className="adv"/>
            <div className="main">
                {/* 최근 체크리스트 */}
                <h2 className="checklist-title">최근에 등록한 체크리스트</h2>
                <p className="more">
                    <a href="#">더보기+</a>
                </p>
            </div>

            {/* 체크리스트 추가 버튼 */}
            <div className="add-checklist">
                <a href="#checklist-create" className="add-button">+</a>
            </div>

            {/* 인기 게시물 섹션 */}
            <div className="post-section">
                <h2 className="section-title">인기 게시물</h2>
                <ul id="popular-posts" className="post-list"></ul>
            </div>
        </div>
    );
}
export default MainPage;
