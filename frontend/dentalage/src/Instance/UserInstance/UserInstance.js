import {StringProcessor} from "../../ServiceModule/Processor/StringProcessor";

export default class UserInstance {
    constructor () {
        this.storageKey = {
            userName:"userName",
            openId:"openId"
        }
    }



    static getInstance () {
        if (!UserInstance.instance) {
            UserInstance.instance = new UserInstance();
        }
        return UserInstance.instance;
    }

    getUserName () {
        return sessionStorage.getItem(this.storageKey.userName)
    }

    loginWithOpenId(openId) {
        sessionStorage.setItem(this.storageKey.openId,openId)
    }

    getOpenId () {
        return sessionStorage.getItem(this.storageKey.openId)
    }

    isLogin (){
        return StringProcessor.Length(this.getOpenId())
    }

    clear () {
        for (var k in this.storageKey) {
            sessionStorage.removeItem(this.storageKey[k])
        }
    }
}

