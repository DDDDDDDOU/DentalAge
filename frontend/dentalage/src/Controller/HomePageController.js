import React from "react";

import logo from "../logo.svg";
import '../App.css';
import DefaultCSSView from "../View/DefaultCSSView";
import HomePageView from "../View/HomePageViews/HomePageView";
import BaseComponent from "../BaseUtil/BaseComponent"

export default class HomePageController extends BaseComponent {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="App">
                <DefaultCSSView></DefaultCSSView>
                <HomePageView></HomePageView>
            </div>
        )
    }
}

