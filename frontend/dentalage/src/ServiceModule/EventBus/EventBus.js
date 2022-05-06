import {StringProcessor} from "../Processor/StringProcessor";

const eventProxy = {
    onObj: {},
    oneObj: {},
    on: function (key, fn) {
        if (this.onObj[key] === undefined) {
            this.onObj[key] = [];
        }
        const args = [].concat(Array.prototype.slice.call(arguments, 1));
        for (let i = 0; i < StringProcessor.Length(args); i++) {
            this.onObj[key].push(args[i]);
        }
    },
    one: function (key, fn) {
        if (this.oneObj[key] === undefined) {
            this.oneObj[key] = [];
        }

        this.oneObj[key].push(fn);
    },
    off: function (key) {
        this.onObj[key] = [];
        this.oneObj[key] = [];
    },
    trigger: function () {
        let key, args;
        if (StringProcessor.Length(arguments) === 0) {
            return false;
        }
        key = arguments[0];
        args = [].concat(Array.prototype.slice.call(arguments, 1));
        if (StringProcessor.Length(this.onObj[key]) > 0) {
            for (let i in this.onObj[key]) {
                this.onObj[key][i].call(null, args[i]);
            }
        }
        if (StringProcessor.Length(this.oneObj[key]) > 0) {
            for (let i in this.oneObj[key]) {
                this.oneObj[key][i].apply(null, args);
                this.oneObj[key][i] = undefined;
            }
            this.oneObj[key] = [];
        }
    }
};

export default eventProxy;
