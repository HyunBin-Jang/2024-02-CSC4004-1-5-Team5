import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './select.css';

const destinations = [
    { country: "Japan", city: "Tokyo" },
    { country: "Japan", city: "Osaka" },
    { country: "Japan", city: "Kyoto" },
    { country: "France", city: "Paris" },
    { country: "France", city: "Nice" },
    { country: "France", city: "Lyon" },
    { country: "USA", city: "New York" },
    { country: "USA", city: "Los Angeles" },
    { country: "USA", city: "Chicago" },
    // ... 나머지 도시들 추가
];

function DestinationSearch() {
    const [searchInput, setSearchInput] = useState("");
    const [filteredDestinations, setFilteredDestinations] = useState([]);
    const navigate = useNavigate(); // useNavigate 훅 사용

    const handleSearchClick = () => {
        const query = searchInput.toLowerCase();
        const filtered = destinations.filter(
            (destination) =>
                destination.city.toLowerCase().includes(query) ||
                destination.country.toLowerCase().includes(query)
        );
        setFilteredDestinations(filtered);
    };

    const handleDestinationClick = (destination) => {
        navigate('/calen', { state: { destination } }); // 선택한 여행지 정보 전달
    };

    return (
        <div>
            <header>
                <h1>새로운 체크리스트</h1>
            </header>
            <form>
                <h2>여행지를 선택해 주세요</h2>
                <h3>여행 목적지(도시 이름)를 검색 목록에서 선택해 주세요.</h3>
            </form>

            <section className="content">
                <div className="search-box">
                    <input
                        type="text"
                        className="search-input"
                        placeholder="여행 도시를 입력하세요."
                        value={searchInput}
                        onChange={(e) => setSearchInput(e.target.value)}
                    />
                    <button
                        type="button"
                        className="search-button"
                        onClick={handleSearchClick}
                    >
                        🔍
                    </button>
                </div>

                <ul className="search-results">
                    {filteredDestinations.length === 0 && searchInput && (
                        <li className="no-results">결과가 없습니다.</li>
                    )}
                    {filteredDestinations.map((destination, index) => (
                        <li
                            key={index}
                            className="destination-item"
                            onClick={() => handleDestinationClick(destination)} // 클릭 이벤트 추가
                        >
                            {destination.city}, {destination.country}
                        </li>
                    ))}
                </ul>
            </section>
        </div>
    );
}

export default DestinationSearch;
