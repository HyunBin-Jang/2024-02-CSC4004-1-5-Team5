// board.js
import React, { useState } from 'react';
import './board.css';
import { useNavigate } from 'react-router-dom';

function Board() {
    const [selectedBoard, setSelectedBoard] = useState(null);
    const navigate = useNavigate();
    const selectBoard = (boardName) => {
        setSelectedBoard(boardName);
        // 게시판 선택 후 해당 게시판 페이지로 이동
        navigate(`/boardlist/${boardName}`);  // boardName을 경로에 포함
    };

    return (
        <div className="board_overlay">
            <header id="titleContainer">
                <img src="/png/back.png" alt="back" className="back" onClick={() => navigate('/')} />
                <h1>게시판</h1>
                <img
                    src="/png/menu.png"
                    alt="menuicon"
                    className="menu"
                    onClick={() => navigate('/menu1')} // 메뉴 페이지로 이동
                />
            </header>
            <div id="mainContainer">
                <div id="boardSelection">
                    <span className="postsList" onClick={() => alert('추후 지원 예정입니다.')}>
                        <img src="/png/post.png" alt="chats" />
                        <button>내가 쓴 글</button>
                    </span>
                    <span className="postsList" onClick={() => navigate('/allboard')}>
                        <img src="/png/chats.png" alt="post" />
                        <button>전체 게시글</button>
                    </span>
                    <span className="postsList" onClick={() => navigate('/hotposts')}>
                        <img src="/png/bestpost.png" alt="bestpost" />
                        <button>BEST 게시글</button>
                    </span>
                    <hr />
                    <button onClick={() => selectBoard('Germany')}>독일 게시판</button>
                    <button onClick={() => selectBoard('Malaysia')}>말레이시아 게시판</button>
                    <button onClick={() => selectBoard('UnitedStates')}>미국 게시판</button>
                    <button onClick={() => selectBoard('Vietnam')}>베트남 게시판</button>
                    <button onClick={() => selectBoard('Spain')}>스페인 게시판</button>
                    <button onClick={() => selectBoard('Singapore')}>싱가포르 게시판</button>
                    <button onClick={() => selectBoard('UnitedKingdom')}>영국 게시판</button>
                    <button onClick={() => selectBoard('Italy')}>이탈리아 게시판</button>
                    <button onClick={() => selectBoard('Indonesia')}>인도네시아 게시판</button>
                    <button onClick={() => selectBoard('Japan')}>일본 게시판</button>
                    <button onClick={() => selectBoard('China')}>중국 게시판</button>
                    <button onClick={() => selectBoard('Canada')}>캐나다 게시판</button>
                    <button onClick={() => selectBoard('Taiwan')}>타이완 게시판</button>
                    <button onClick={() => selectBoard('Thailand')}>태국 게시판</button>
                    <button onClick={() => selectBoard('France')}>프랑스 게시판</button>
                    <button onClick={() => selectBoard('Philippines')}>필리핀 게시판</button>
                    <button onClick={() => selectBoard('Australia')}>호주 게시판</button>
                    <button onClick={() => selectBoard('Hongkong')}>홍콩 게시판</button>
                </div>
            </div>
        </div>
    );
}

export default Board;