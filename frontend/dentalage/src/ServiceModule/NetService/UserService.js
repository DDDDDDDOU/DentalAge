import axios from "axios";
import {errorNotification} from "../Utils/Notifications";
import {responceDispatcher} from "./Utils/ResponceDispatcher";
import URLInstance from "../../Instance/URLInstance/URLInstance";

export  function getWXTicket(callback){
    let URL=URLInstance.getInstance().BackendServerURL+"qrCodeFirstLogin/getQrCode"
    axios.get(URL).then((res)=>{
        responceDispatcher(res,callback)
    }).catch(err=>{
        console.log(err)
        errorNotification("网络异常","请检查网络设置",3)
    })
}


export  function getOppenId(sceneStr,callback){
    let URL=URLInstance.getInstance().BackendServerURL+"qrCodeFirstLogin/getOpenId?eventKey="+sceneStr
    axios.get(URL).then((res)=>{
        responceDispatcher(res,callback)
    }).catch(err=>{


    })
}