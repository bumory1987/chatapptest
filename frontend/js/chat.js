const url = 'http://localhost:8087';
let stompClient;
let selectedUser;

function connectToChat(userName){
    console.log("connecting to chat ....."+ userName)
    console.log("hi")
    let socket = new SockJS(url + "/chat")
    console.log(socket)
    stompClient = Stomp.over(socket);
    console.log(stompClient)
    stompClient.connect({}, function(frame) {
       console.log("connected to : " + frame );
       stompClient.subscribe("/topic/message/"+userName, function(response){
           let data = JSON.parse(response.body);
           render(data.message, data.fromLogin)
       });
    })
}

sendMsg = (from , text) => {
    console.log(stompClient)
    stompClient.send("/app/chat/"+ selectedUser, {} , JSON.stringify({
        fromLogin :  from,
        message : text
    }))
}


function registration() {
    let username = document.getElementById("userName").value;
    console.log(username)
    $.get(url + "/registration/" + username, function (response) {
        connectToChat(username);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Login is already busy!")
        }
    })
}


selectUser = (userName)=>{
    console.log("selecting users: " + userName);
    selectedUser = userName;
    let isNew = document.getElementById("newMessage_" + userName) !== null;
    if (isNew) {
        let element = document.getElementById("newMessage_" + userName);
        element.parentNode.removeChild(element);
        render(newMessages.get(userName), userName);
    }
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat with ' + userName);
}




function fetchAll() {
    $.get(url + "/fetchAllUsers", function (response) {
        let users = response;
        console.log(response)
        let usersTemplateHTML = "";
        for (let i = 0; i < users.length; i++) {
            usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i] + '\')"><li class="clearfix">\n' +
                '                <img src="https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_1280.png" width="55px" height="55px" alt="avatar" />\n' +
                '                <div class="about">\n' +
                '                    <div id="userNameAppender_' + users[i] + '" class="name">' + users[i] + '</div>\n' +
                '                    <div class="status">\n' +
                '                        <i class="fa fa-circle offline"></i>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </li></a>';
        }
        $('#usersList').html(usersTemplateHTML);
    });
}



document.getElementById("testbutton").onclick =  function (){
    registration();
};


document.getElementById("fetchman").onclick =  function (){
    console.log("hello");
    fetchAll();
};

