function generateGameBoard(){
    var existingcontainer = document.getElementById("gameboardcontainer");
    if (existingcontainer){
        existingcontainer.remove();
    }
    console.log("running");
    const rewards = [["11","12","13"], ["21","22","23"],["31","32","33"]];
    var container = document.createElement('div');
    container.id = "gameboardcontainer";
    var gameBoard = document.createElement('div');
    var button;
    console.log(rewards[0][0]);
    for(i = 0; i <rewards.length; i++){
        for(j = 0; j<rewards[i].length; j++){
            button = document.createElement('input');
            button.type = "button";
            button.value = rewards[i][j];
            gameBoard.appendChild(button);
            container.appendChild(gameBoard);
        }

    }
    var element = document.getElementById("gameboard");
    element.appendChild(container);
    //("#downloadContent").prepend(container);
}