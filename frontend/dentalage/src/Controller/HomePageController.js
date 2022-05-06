import React from "react";

import logo from "../logo.svg";
import '../App.css';
import DefaultCSSView from "../View/DefaultCSSView";

export default class HomePageController extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="App">
                <DefaultCSSView></DefaultCSSView>
            </div>
        )
    }
}
