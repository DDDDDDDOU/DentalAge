import React from "react";

import {Upload, message, Button} from 'antd';
import {InboxOutlined} from '@ant-design/icons';
import COSInstance from "../../Instance/COSSettings/COSInstance";
import {StringProcessor} from "../../ServiceModule/Processor/StringProcessor";
import {ImageProcessor} from "../../ServiceModule/Processor/ImageProcessor";
import "../../CSS/FileUpLoaderViewCSS.css"
import {DentalPredict} from "../../ServiceModule/NetService/PredService";

const {Dragger} = Upload;

export default class FileUpLoaderView extends React.Component {
    constructor(props) {
        super(props);
        this.draggerProps = {
            name: 'file',
            accept: 'image/*',
            multiple: true,
            listType: 'picture',
            maxCount: 1,
            customRequest: (info) => {
                // 上传逻辑，上传到COS
                COSInstance.getInstance().putObject(info.file, (err, data) => {
                    if (err) {
                        this.setState({
                            fileLocation: "",
                            fileBase64:null
                        }, () => {
                            console.log(err)
                            info.onError(info.file)
                            message.error('文件上传失败，请稍后再次上传')
                        })
                    } else {

                        this.setState({
                            fileLocation: data.Location,
                        }, () => {
                            info.onSuccess(info.file)
                            console.log(data.Location)
                            message.success(` ${info.file.name} file uploaded successfully.`);
                        })
                    }
                })

            },
            onChange: (info) => {
                if (info.file.status === 'done') {
                    ImageProcessor.image2Base64(info.fileList[0].originFileObj, (result) => {
                            this.setState({
                                fileBase64:result
                            })
                        }
                    )
                } else {
                    this.setState({
                        showDentalAge:false
                    })
                }
            },
            onDrop: (e) => {
                console.log('Dropped files', e.dataTransfer.files);
            },
            onRemove: (file) => {
                this.setState({
                    fileLocation: "",
                    fileBase64:null
                }, () => {
                    return true
                })
            }
        }

        this.state = {
            fileLocation: "",
            fileBase64:null,
            isMale:-1,
            dentalAge:0,
            showDentalAge:false
        }
    }


    /* events */
    onPredButtonClicked = (e) =>{
        this.props.setLoadingState(true)
        DentalPredict(this.state.fileBase64,this.state.isMale,(res)=>{
            this.setState({
                dentalAge:res,
                showDentalAge:true
            },()=>{
                this.props.setLoadingState(false)
                console.log(res)
            })
        })
    }

    switchGender = (isMale) => {
        this.setState({
            isMale:isMale
        })
    }
    /* renderer */
    predButtonRenderer = () => {
        if (StringProcessor.Length(this.state.fileLocation)) {
            return (
                <div className={"predButtonContainer"}>
                    <Button className={"predButton"} disabled={this.state.isMale === -1} onClick={(e)=>{this.onPredButtonClicked(e)}}>预测</Button>
                </div>

            )
        } else {
            return <div></div>
        }
    }

    genderButtonRenderer = () =>{
        if (StringProcessor.Length(this.state.fileLocation)) {
            return (
                <div className={"genderButtonContainer"}>
                    <button className={"genderButton maleButton " + (this.state.isMale === true ? "maleSelected" : "")} onClick={()=>this.switchGender(true)}>
                        我是男生
                    </button>
                    <button className={"genderButton femaleButton " + (this.state.isMale === false ? "femaleSelected" : "")} onClick={()=>this.switchGender(false)}>
                        我是女生
                    </button>
                </div>
            )
        } else {
            return <div></div>
        }
    }

    dentalAgeLabelRenderer = () =>{
        if (this.state.showDentalAge) {
            return <div className={"dentalAgeLabel"}><span>预测牙龄为：{this.state.dentalAge}岁</span></div>
        } else {
            return <div></div>
        }
    }
    render() {
        return (
            <div>
                <Dragger {...this.draggerProps}>
                    <p className="ant-upload-drag-icon">
                        <InboxOutlined/>
                    </p>
                    <p className="ant-upload-text">Click or drag file to this area to upload</p>
                    <p className="ant-upload-hint">
                        Support for a single or bulk upload. Strictly prohibit from uploading company data or other
                        band files
                    </p>
                </Dragger>
                {this.genderButtonRenderer()}
                {this.predButtonRenderer()}
                {this.dentalAgeLabelRenderer()}
            </div>
        )
    }
}

