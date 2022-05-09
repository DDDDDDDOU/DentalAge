import React from "react";

import "../../CSS/HomePageViewCSS.css"
import {getOppenId, getWXTicket} from "../../ServiceModule/NetService/UserService";
import {StringProcessor} from "../../ServiceModule/Processor/StringProcessor";
import {Spin} from "antd";
import URLInstance from "../../Instance/URLInstance/URLInstance";

export default class HomePageView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            ticket:"",
            sceneStr:""
        }
    }

    componentWillMount() {
        getWXTicket((res)=>{
            this.setState({
                ticket:res.data.ticket,
                sceneStr:res.data.sceneStr
            },() => {
                this.getUserOppenId()
            })
        })
    }

    getUserOppenId = () =>{
        if (StringProcessor.Length(this.state.sceneStr)) {
            let interval = setInterval(()=>{
                getOppenId(this.state.sceneStr,(res)=>{
                    if(parseInt(res.code) === 200) {
                        window.location.href = URLInstance.getInstance().constructFrontendURL("dentalage")
                        clearInterval(interval)
                    }
                })
            },2000)
        }


    }

    render() {
        return (
            <div className={"HomePageViewContainer"}>
                <div className={"HomePageViewTicketContainer"}>
                    <Spin size={"large"}  spinning={!StringProcessor.Length(this.state.ticket)}>
                        <div className={"ticketImageContainer"}>
                            {
                                StringProcessor.Length(this.state.ticket)
                                    ? <img   className={"ticketImage"} src = {'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket='+this.state.ticket} />
                                    : <div></div>
                            }
                        </div>

                    </Spin>
                </div>

            </div>
        )
    }
}