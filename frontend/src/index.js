import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import './index.css'; // Importing index.css file
import "tailwindcss/tailwind.css";

import Home from './components/Home';


function App() {
  // Define an array of paths where you want the Navbar to be rendered
  // const navbarPaths = ['/home', '/co/', '/createco', '/enterprise', '/talent', '/user', '/createuser', '/createmile/'];
  // const user = JSON.parse(localStorage.getItem('user'));
  // Check if the current path is in the array of navbarPaths
  // const shouldRenderNavbar = navbarPaths.some((path) => window.location.pathname.startsWith(path));

  return (
    <Router>
      <Routes>
        <Route path="/*" element={<Home />} />
        {/* <Route path="/login" element={<Login />} />
        <Route path="/home" element={<Home />} /> */}
        {/* <Route path="/co/:orderNumber" element={<CO />} />
        <Route path="/createco" element={<CreateCO />} />
        <Route path="/createmile/:orderNumber" element={<CreateMilestone />} />
        <Route path="/enterprise" element={<Enterprise />} />
        <Route path="/talent" element={<Talent />} /> */}
        {/* {user && user.roles && user.roles.includes('ROLE_ADMIN') && (
          <>
            <Route path="/user" element={<User />} />
            <Route path="/createuser" element={<CreateUser />} />
          </>
        )} */}
      </Routes>
    </Router>
  );
}

render(<App />, document.getElementById('root'));

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );

// // If you want to start measuring performance in your app, pass a function
// // to log results (for example: reportWebVitals(console.log))
// // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
