import {errorNotification} from "../../Utils/Notifications";

export function responceDispatcher(res, callback=()=>{}){
    //处理http返回结果
    switch (parseInt(res.status)) {
        default:
            callback(res.data)
    }
}