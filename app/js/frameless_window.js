var ebus =new vertx.EventBus('http://127.0.0.1:30533/eventbus');


window.onfocus = function() { 
  console.log("focus");
  focusTitlebars(true);
}

window.onblur = function() { 
  console.log("blur");
  focusTitlebars(false);
}

window.onresize = function() {
  updateContentStyle();
}

window.onload = function() {
  addTitlebar("top-titlebar", "top-titlebar.png", "Top Titlebar");
  addTitlebar("left-titlebar", "left-titlebar.png", "Left Titlebar");

  document.getElementById("close-window-button").onclick = function() {
      alert('exit');
      ebus.send('app.agent.exit',{});
      window.close();
  }
  
  updateContentStyle();
  require("nw.gui").Window.get().show();
}
