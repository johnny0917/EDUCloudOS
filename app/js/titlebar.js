function closeWindow() {
  ebus.send('app.agent.exit',{});
  window.close();
}

function updateImageUrl(image_id, new_image_url) {
  var image = document.getElementById(image_id);
  if (image)
    image.src = new_image_url;
}

function createImage(image_id, image_url) {
  var image = document.createElement("img");
  image.setAttribute("id", image_id);
  image.src = image_url;
  return image;
}

function createButton(button_id, button_name, normal_image_url,
                       hover_image_url, click_func) {
  var button = document.createElement("div");
  button.setAttribute("class", button_name);
  var button_img = createImage(button_id, normal_image_url);
  button.appendChild(button_img);
  button.onmouseover = function() {
    updateImageUrl(button_id, hover_image_url);
  }
  button.onmouseout = function() {
    updateImageUrl(button_id, normal_image_url);
  }
  button.onclick = click_func;
  return button;
}

function focusTitlebars(focus) {
  var bg_color = focus ? "#3a3d3d" : "#7a7c7c";
    
  var titlebar = document.getElementById("top-titlebar");
  if (titlebar)
    titlebar.style.backgroundColor = bg_color;
  titlebar = document.getElementById("bottom-titlebar");
  if (titlebar)
    titlebar.style.backgroundColor = bg_color;
  titlebar = document.getElementById("left-titlebar");
  if (titlebar)
    titlebar.style.backgroundColor = bg_color;
  titlebar = document.getElementById("right-titlebar");
  if (titlebar)
    titlebar.style.backgroundColor = bg_color;
}

function addTitlebar(titlebar_name, titlebar_icon_url, titlebar_text) {
  var titlebar = document.createElement("div");
  titlebar.setAttribute("id", titlebar_name);
  titlebar.setAttribute("class", titlebar_name);
  
  document.body.appendChild(titlebar);
}

function updateContentStyle() {
	  var content = document.getElementById("content");
	  if (!content)
	    return;

	  var left = 0;
	  var top = 0;
	  var width = window.outerWidth;
	  var height = window.outerHeight;

	  var titlebar = document.getElementById("top-titlebar");
	  if (titlebar) {
	    height -= titlebar.offsetHeight;
	    top += titlebar.offsetHeight;
	  }
	  titlebar = document.getElementById("bottom-titlebar");
	  if (titlebar) {
	    height -= titlebar.offsetHeight;
	  }
	  titlebar = document.getElementById("left-titlebar");
	  if (titlebar) {
	    width -= titlebar.offsetWidth;
	    left += titlebar.offsetWidth;
	  }
	  titlebar = document.getElementById("right-titlebar");
	  if (titlebar) {
	    width -= titlebar.offsetWidth;
	  }

	  var contentStyle = "position: absolute; ";
	  contentStyle += "left: " + left + "px; ";
	  contentStyle += "top: " + top + "px; ";
	  contentStyle += "width: " + width + "px; ";
	  contentStyle += "height: " + height + "px; ";
	  content.setAttribute("style", contentStyle);
	}