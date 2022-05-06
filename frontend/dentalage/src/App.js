import React from 'react';
import 'antd/dist/antd.css';
import {
    BrowserRouter,
    Routes,
    Route,
} from "react-router-dom";

import HomePageController from "./Controller/HomePageController";
import DentalAgeController from "./Controller/DentalAgeController";
export default class App extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePageController />} />
                <Route path="/dentalage" element={<DentalAgeController />} />
            </Routes>
        </BrowserRouter>

    );
  }
}
