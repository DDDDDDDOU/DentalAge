import React from "react";
import FileUpLoaderView from "../View/DentalAge/FileUpLoaderView";
import {Spin} from "antd";
import BaseComponent from "../BaseUtil/BaseComponent"

export default class DentalAgeController extends BaseComponent {
    constructor(props) {
        super(props);
        this.state = {
            loading:false
        }
    }

    setLoadingState = (isLoading) => {
        this.setState({
            loading:isLoading
        })
    }

    render() {
        return (
            <div>
                <Spin spinning={this.state.loading} delay={0}>
                    <FileUpLoaderView
                        setLoadingState={this.setLoadingState}
                    />
                </Spin>
            </div>
        )
    }

}

