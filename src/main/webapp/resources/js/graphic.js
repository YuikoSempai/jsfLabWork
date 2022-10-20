let input;
let myArray = [];

function setInput() {//set input from <radio>
    input = document.getElementById('form:r_input').value;
    drawGraphic();
    drawDots();
}

function drawGraphic() {//draw Graphic with input radius
    let parameter = input
    let canvas = document.getElementById('graphic');
    if (canvas.getContext) {
        let ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.fillStyle = "rgba(256, 256, 256, 0.8)"; // background fill

        ctx.fillStyle = 'rgb(35, 184, 253)'; //area

        //circle
        ctx.moveTo(200, 200);
        ctx.arc(200, 200, parameter * 40, 0, Math.PI / 2);
        ctx.fill();

        //rectangle
        ctx.fillRect(200 - parameter * 40, 200 - parameter * 20, parameter * 40, parameter * 20);

        //triangle
        ctx.moveTo(200, 200 - parameter * 40);
        ctx.lineTo(200 + parameter * 20, 200);
        ctx.lineTo(200, 200);
        ctx.fill();

        for (let x = 40; x < 361; x += 40) { // gird
            ctx.moveTo(x, 0);
            ctx.lineTo(x, 400);
        }
        for (let y = 40; y < 361; y += 40) {
            ctx.moveTo(0, y);
            ctx.lineTo(400, y);
        }
        ctx.strokeStyle = "#333";
        ctx.stroke();
        ctx.beginPath();
        // getAllElements(parameter);
    }
}

let rounded = function (number) {
    return +number.toFixed(2);
}

function getCursorPosition(canvas, event){
    if (document.getElementById('form:r_input').value !== "") {
        const rect = canvas.getBoundingClientRect()
        let x = rounded((-1) * (200 - (event.clientX - rect.left)) / 40);
        let y = rounded((200 - (event.clientY - rect.top)) / 40);
        let form = document.getElementById('form');
        sendData(x, y);
    } else {
        alert("Select radius first")
    }
}

function addEventListenerToCanvas() {
    const canvas = document.getElementById('graphic')
    canvas.addEventListener('mousedown', function (e) {
        getCursorPosition(canvas, e);
    })
}

function sendData(x, y){
    let xField = document.getElementById('form:x');
    xField.value = x;
    let yField = document.getElementById('form:y');
    yField.value = y;
    let button = document.getElementById('form:sendButton');
    button.click();
}
addEventListenerToCanvas();
getAllDots();
function getAllDots(){
    let size = "#{mainBean.dataList.size()}";
    for(let i=0;i<size;i++){
        let str = "{mainBean.dataList.get(" + i + ")}";
        let element = "#" + str;
        let x = element.split(" ")[0];
        let y = element.split(" ")[1];
        let r = element.split(" ")[2];
        let status = element.split(" ")[3];
        console.log(x + " " + y + " " + r + " " + status);
    }
}

function drawDots(){
    console.log("draw");
    for(let i=0;i<myArray.length;i++){
        let x = myArray[i].split(" ")[0];
        let y = myArray[i].split(" ")[1];
        let r = myArray[i].split(" ")[2];
        if(r!==document.getElementById('form:r_input').value){
            continue;
        }
        let status = myArray[i].split(" ")[3];
        let canvas = document.getElementById('graphic');
        let ctx = canvas.getContext('2d');
        let circle = new Path2D();
        circle.moveTo(200 + x * 40, 200 - y * 40);
        circle.arc(200 + x * 40, 200 - y * 40, 10, 0, 2 * Math.PI)
        if (status === "true") {
            //green
            ctx.fillStyle = 'green';
        } else {
            //black
            ctx.fillStyle = 'red';
        }
        ctx.fill(circle);
    }
}

function onlyDigits() {
    var separator = ".";
    var replaced = new RegExp('[^\\d\\'+separator+'\\-]', "g");
    var regex = new RegExp('\\'+separator, "g");
    this.value = this.value.replace(replaced, "");

    var minValue = parseFloat(-5);
    var maxValue = parseFloat(3);
    var val = parseFloat(separator === "." ? this.value : this.value.replace(new RegExp(separator, "g"), "."));

    if (minValue <= maxValue) {
        if (this.value[0] === "-") {
            if (this.value.length > 8 ) {
                this.value = this.value.substr(0, 8);
            }
        } else {
            if (this.value.length > 7) {
                this.value = this.value.substr(0, 7);
            }
        }

        if (this.value[0] === separator) {
            this.value = "0" + this.value;
        }

        if (minValue < 0 && maxValue < 0) {
            if (this.value[0] !== "-")
                this.value = "-" + this.value[0];
        } else if (minValue >= 0 && maxValue >= 0) {
            if (this.value[0] === "-")
                this.value = this.value.substr(0, 0);
        }

        if (val < minValue || val > maxValue) {
            this.value = this.value.substr(0, 0);
        }
        if (this.value.match(regex))
            if (this.value.match(regex).length > 1) {
                this.value = this.value.substr(0, 0);
            }

        if (this.value.match(/\-/g))
            if (this.value.match(/\-/g).length > 1) {
                this.value = this.value.substr(0, 0);
            }

    }
}