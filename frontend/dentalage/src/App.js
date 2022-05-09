import React from 'react';
import 'antd/dist/antd.css';
import {
    BrowserRouter,
    Route, Switch,
} from "react-router-dom";

import HomePageController from "./Controller/HomePageController";
import DentalAgeController from "./Controller/DentalAgeController";

function empty(){
    return <div></div>
}

export default class App extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
        <BrowserRouter>
            <Switch>

                <Route path={"/dentalage"} component={DentalAgeController}/>
                <Route exact path={"/empty"} component={empty}/>
                <Route exact path={"/"} component={HomePageController}/>

            </Switch>
        </BrowserRouter>

    );
  }
}
