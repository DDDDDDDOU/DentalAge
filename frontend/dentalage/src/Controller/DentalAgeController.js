import React from "react";
import FileUpLoaderView from "../View/DentalAge/FileUpLoaderView";
import {Spin} from "antd";

export default class DentalAgeController extends React.Component {
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