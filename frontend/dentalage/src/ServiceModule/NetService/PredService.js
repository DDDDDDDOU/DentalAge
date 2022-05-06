

import axios from "axios";
import {errorNotification} from "../Utils/Notifications";
import {responceDispatcher} from "./Utils/ResponceDispatcher";
import URLInstance from "../../Instance/URLInstance/URLInstance";

export  function DentalPredict(imageBase64,isMale,callback){
    let URL=URLInstance.getInstance().DentalPredictServerURL+`dental_age`
    let data = {
        img:imageBase64,
        gender:Number(isMale)
    }
    axios.post(URL,data).then((res)=>{
        responceDispatcher(res,callback)
    }).catch(err=>{
        console.log(err)
        errorNotification("网络异常","请检查网络设置",3)
    })
}