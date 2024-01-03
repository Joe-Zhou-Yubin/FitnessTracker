import React, { useState } from 'react';
import './Home.css'; 

function Home() {
    const [selectedMonth, setSelectedMonth] = useState(''); // State to store selected month
    const daysInMonth = {
        '01': 31, '02': 28, '03': 31, '04': 30, '05': 31, '06': 30,
        '07': 31, '08': 31, '09': 30, '10': 31, '11': 30, '12': 31,
    }; // Define days in each month

    const handleMonthChange = (e) => {
        setSelectedMonth(e.target.value); // Update selected month
    };

    const generateDays = (num) => {
        return Array.from({ length: num }, (_, i) => i + 1); // Generate numbers 1 to num
    };

    const numbers = selectedMonth ? generateDays(daysInMonth[selectedMonth]) : []; // Generate days based on selected month

    return (
        <div className="home">
            <div className="topBar">
                <select className="monthContent" onChange={handleMonthChange} value={selectedMonth}>
                    <option value='' disabled>month</option>
                    <option value='01'>january</option>
                    <option value='02'>february</option>
                    <option value='03'>march</option>
                    <option value='04'>april</option>
                    <option value='05'>may</option>
                    <option value='06'>june</option>
                    <option value='07'>july</option>
                    <option value='08'>august</option>
                    <option value='09'>september</option>
                    <option value='10'>october</option>
                    <option value='11'>november</option>
                    <option value='12'>december</option>
                </select>
            </div>
            <div className="dayBar"> {/* Added flex-wrap and gap for spacing */}
                {numbers.map((number) => (
                    <button className="day" key={number}>
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
                        <button className='category'>
                            abs
                        </button>
                        <button className='category'>
                            arm
                        </button>
                        <button className='category'>
                            ass / glutes
                        </button>
                        <button className='category'>
                            back
                        </button>
                        <button className='category'>
                            leg
                        </button>
                    </div>
                </div>
                <div className='activityColumn'>
                    hi
                </div>
            </div>
        </div>
    )
}

export default Home;
