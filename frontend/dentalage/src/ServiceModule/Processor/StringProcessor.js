export class StringProcessor{
    static Length(str){
        //计算字符串长度或者元素个数
        //对于undefined对象或者NAN返回0
        if(str===undefined || str===null) return 0
        else return str.length === undefined ?  0 : str.length
    }
}