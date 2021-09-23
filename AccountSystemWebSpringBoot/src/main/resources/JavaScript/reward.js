function generateRewardCheckBoxes(){
    var existingcontainer = document.getElementById("checkboxes");
    if (existingcontainer){
        existingcontainer.remove();
    }
    console.log("running");
    const rewards = ["reward 1", "apple","file"];
    var container = document.createElement('div');
    container.id = "checkboxes";
    var checkboxContainer = document.createElement('div');
    checkboxContainer.setAttribute('class', 'checkbox');
    var label;
    var checkBox;
    var breakLine;
    for(i=0; i<rewards.length; i++){
        label = document.createElement('label');
        checkBox = document.createElement('input');
        breakLine = document.createElement('br');
        checkBox.type = "checkbox";
        checkBox.name = rewards[i];
        checkBox.value = rewards[i];
        label.innerHTML = rewards[i];
        label.appendChild(checkBox);
        label.appendChild(breakLine);
        checkboxContainer.appendChild(label);
        container.appendChild(checkboxContainer);
    }
    var element = document.getElementById("checkboxcontainer");
    element.appendChild(container);
    //("#downloadContent").prepend(container);
}