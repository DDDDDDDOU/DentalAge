export default class URLInstance {
    constructor () {
        this.DentalPredictServerURL = "http://1.13.19.206:5000/"
        this.BackendServerURL = "http://1.13.19.206:5000/"
    }



    static getInstance () {
        if (!URLInstance.instance) {
            URLInstance.instance = new URLInstance();
        }
        return URLInstance.instance;
    }

    getDentalPredictServerURL () {
        return this.DentalPredictServerURL
    }

    getBackendServerURL () {
        return this.BackendServerURL
    }


}

