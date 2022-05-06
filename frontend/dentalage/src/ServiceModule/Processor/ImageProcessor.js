export class ImageProcessor {
    static image2Base64 (imageFile,callback) {
        let reader = new FileReader();
        reader.onloadend = ()=> {
            let splitData = reader.result.split(',')
            callback(splitData[splitData.length - 1])
        }
        reader.readAsDataURL(imageFile);
    }
}