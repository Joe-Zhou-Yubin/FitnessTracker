import React, { useState, useEffect, useMemo } from 'react';
import './Home.css'; 

function Home() {
    const [selectedMonth, setSelectedMonth] = useState('');
    const [selectedDate, setSelectedDate] = useState('');
    const [displayedActivity, setDisplayedActivity] = useState('');

    const daysInMonth = {
        '01': 31, '02': 28, '03': 31, '04': 30, '05': 31, '06': 30,
        '07': 31, '08': 31, '09': 30, '10': 31, '11': 30, '12': 31,
    };

    const monthNames = useMemo(() => ({
        '01': 'January', '02': 'February', '03': 'March', '04': 'April',
        '05': 'May', '06': 'June', '07': 'July', '08': 'August',
        '09': 'September', '10': 'October', '11': 'November', '12': 'December',
    }), []);

    const handleMonthChange = (e) => {
        setSelectedMonth(e.target.value);
    };

    const generateDays = (num) => {
        return Array.from({ length: num }, (_, i) => i + 1);
    };

    const numbers = selectedMonth ? generateDays(daysInMonth[selectedMonth]) : [];

    const handleDayClick = (day) => {
        setSelectedDate(day);
    };

    useEffect(() => {
        // Set default date on initial load (today's date)
        const today = new Date();
        const formattedToday = `${monthNames[String(today.getMonth() + 1).padStart(2, '0')]} ${String(today.getDate()).padStart(2, '0')}`;
        setSelectedDate(formattedToday);
    }, [monthNames]);

    useEffect(() => {
        // Update displayedActivity when both month and date are selected
        if (selectedMonth && selectedDate) {
            const monthName = monthNames[selectedMonth];
            setDisplayedActivity(`Activity for ${monthName}, ${selectedDate}`);
            // Fetch and set activity data based on selectedMonth and selectedDate
            // fetchData(selectedMonth, selectedDate); // You can fetch data here
        }
    }, [selectedMonth, selectedDate, monthNames]);

    return (
        <div className="home">
            <div className="topBar">
                <select className="monthSelected" onChange={handleMonthChange} value={selectedMonth}>
                    <option value='' disabled>month</option>
                    {Object.keys(monthNames).map((key) => (
                        <option key={key} value={key}>{monthNames[key]}</option>
                    ))}
                </select>
            </div>
            <div className="dayBar">
                {numbers.map((number) => (
                    <button className="day" key={number} onClick={() => handleDayClick(`${String(number).padStart(2, '0')}`)}>
                        {number}
                    </button>
                ))}
            </div>
            <div className='dayContent'>
                <div className='categoryColumn'>
                    <div>
                        <h3>today's target</h3>
                    </div>
                    <div className='categoryList'>
                        {/* Buttons for categories */}
                    </div>
                </div>
                <div className='activityColumn'>
                    <h3>{displayedActivity}</h3>
                    {/* Render activity content */}
                </div>
            </div>
        </div>
    )
}

export default Home;
