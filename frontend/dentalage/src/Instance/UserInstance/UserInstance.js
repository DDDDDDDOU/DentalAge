import {StringProcessor} from "../../ServiceModule/Processor/StringProcessor";

export default class UserInstance {
    constructor () {
        this.storageKey = {
            userName:"userName",
            token:"token"
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

    loginWithToken(token) {
        sessionStorage.setItem(this.storageKey.token,token)
    }

    getToken () {
        return sessionStorage.getItem(this.storageKey.token)
    }

    isLogin (){
        return StringProcessor.Length(this.getToken())
    }

    clear () {
        for (var k in this.storageKey) {
            sessionStorage.removeItem(this.storageKey[k])
        }
    }
}

