let input;
let myArray = [];

function setInput() {//set input from <radio>
    input = document.getElementById('form:r_input').value;
    drawGraphic();
    setTimeout(getAllDots(),500)
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

function getCursorPosition(canvas, event) {
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

function sendData(x, y) {
    let xField = document.getElementById('form:x');
    xField.value = x;
    let yField = document.getElementById('form:y');
    yField.value = y;
    let button = document.getElementById('form:sendButton');
    button.click();
    setTimeout(getAllDots,1000);
}

addEventListenerToCanvas();

function getAllDots() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "/jsfLabWork-1.0/info", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let array = xhr.response.split('\n');
                for (let i = 0; i < array.length - 1; i++) {
                    let arrayWithStatus = array[i].split(' ')
                    let x = arrayWithStatus[1];
                    let y = arrayWithStatus[2];
                    let R = arrayWithStatus[3];
                    let status = arrayWithStatus[4];
                    let shape = arrayWithStatus[5];
                    let size = arrayWithStatus[6];
                    if (input == R) {
                        drawDot(x, y, R, status, shape, size)
                    }
                }
            }
        }
    }
}

function drawDot(x, y, R, status, shape, size) {
    let canvas = document.getElementById('graphic');
    let ctx = canvas.getContext('2d');
    let shapeForm;
    if (shape === 'circle') {
        shapeForm = new Path2D();
        shapeForm.moveTo(200 + x * 40, 200 - y * 40);
        shapeForm.arc(200 + x * 40, 200 - y * 40, 5 * size, 0, 2 * Math.PI)
    }else{
        shapeForm = new Path2D();
        shapeForm.rect(200+x*40, 200-y*40, 5 *size, 5 * size);
    }
    if (status) {
        //green
        ctx.fillStyle = 'green';
    } else {
        //black
        ctx.fillStyle = 'red';
    }
    ctx.fill(shapeForm);
}

function deleteAllData() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/jsfLabWork-1.0/info", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send();
    window.location.reload();
}