import React from "react";
import URLInstance from "../Instance/URLInstance/URLInstance";
import UserInstance from "../Instance/UserInstance/UserInstance";

export default class BaseComponent extends React.Component {
    constructor(props) {
        super(props);
        if (!UserInstance.getInstance().isLogin() && window.location.href !== URLInstance.getInstance().getFrontendURL()) {
            window.location.href = URLInstance.getInstance().getFrontendURL()
        }}
}

