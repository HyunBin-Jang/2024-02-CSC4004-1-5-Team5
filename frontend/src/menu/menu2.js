import React from "react";
import "../style.css";

function Menu2({ onClose }) {
    return (
        <div className="menu-overlay">
            <div className="menu-container">
                <header className="menu-header">
                    <img src="/png/logo.png" alt="logo" className="logo" />
                    <h1>TRAVEL KIT</h1>
                    <img
                        src="/png/delete.png"
                        alt="close menu"
                        className="delete-icon"
                        onClick={onClose}
                    />
                </header>
                <nav>
                    <h3>
                        <a href="#">나의 체크리스트</a>
                        <br />
                        <br />
                        <a href="#">게시판</a>
                        <br />
                        <br />
                        <a href="#">로그인</a>
                        <br />
                        <br />
                    </h3>
                </nav>
            </div>
        </div>
    );
}
export default Menu2;
