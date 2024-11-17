import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import Flatpickr from 'react-flatpickr';
import 'flatpickr/dist/flatpickr.min.css';
import './calen.css'; // 필요하면 추가

function Calen() {
    const location = useLocation();
    const { destination } = location.state || {};
    const [selectedDates, setSelectedDates] = useState([]);

    const handleDateChange = (dates) => {
        setSelectedDates(dates);
    };

    const handleCreateChecklist = () => {
        if (selectedDates.length === 2) {
            alert(`Checklist created for: ${selectedDates[0].toLocaleDateString()} to ${selectedDates[1].toLocaleDateString()}`);
            // 여기에 추가 로직 구현 (예: 상태 저장, API 호출)
        } else {
            alert('Please select both a departure and arrival date.');
        }
    };
    return (
        <div>
            <header>
                <h1>새로운 체크리스트</h1>
            </header>

            <form>
                <h2>출발날짜와 도착날짜를 선택하세요.</h2>
                {destination ? (
                    <p>
                        선택된 여행지: {destination.city}, {destination.country}
                    </p>
                ) : (
                    <p>여행지가 선택되지 않았습니다.</p>
                )}
                <div className="calendar">
                    <Flatpickr
                        id="date-picker"
                        options={{
                            mode: 'range', // 범위 선택 모드
                            inline: true,  // 항상 캘린더 표시
                            dateFormat: 'Y.m.d', // 날짜 형식
                        }}
                        onChange={handleDateChange} // 날짜 변경 핸들러
                    />
                </div>
                <div id="button-container">
                    {selectedDates.length === 2 && (
                        <button
                            type="button"
                            className="create-checklist-button"
                            onClick={handleCreateChecklist}
                        >
                            체크리스트 생성
                        </button>
                    )}
                </div>
            </form>
        </div>
    );
}

export default Calen;
