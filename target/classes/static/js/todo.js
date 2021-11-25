function openPanel()
{
    var leftPanel = document.getElementById("left-panel");

    if (window.matchMedia("(max-width: 35.5em)").matches)
    {
        leftPanel.classList.remove("pure-u-sm-8-24");
        leftPanel.classList.remove("pure-u-md-6-24");
        leftPanel.classList.remove("pure-u-xl-4-24"); 
        leftPanel.classList.add("pure-u-1");
    }
    else
    {
        leftPanel.classList.add("pure-u-sm-8-24");
        leftPanel.classList.add("pure-u-md-6-24");
        leftPanel.classList.add("pure-u-xl-4-24");
    }

    document.getElementById("button-open-left-panel").style.display = null;
    
    var contentWrapper = document.getElementById("content-wrapper");
    contentWrapper.classList.add("pure-u-sm-16-24");
    contentWrapper.classList.add("pure-u-md-18-24");
    contentWrapper.classList.add("pure-u-xl-20-24");
}

function closePanel() 
{
    document.getElementById("button-open-left-panel").style.display = "block";
    
    var leftPanel = document.getElementById("left-panel");
    leftPanel.classList.remove("pure-u-1");
    leftPanel.classList.remove("pure-u-sm-8-24");
    leftPanel.classList.remove("pure-u-md-6-24");
    leftPanel.classList.remove("pure-u-xl-4-24");

    var contentWrapper = document.getElementById("content-wrapper");
    contentWrapper.classList.remove("pure-u-sm-16-24");
    contentWrapper.classList.remove("pure-u-md-18-24");
    contentWrapper.classList.remove("pure-u-xl-20-24");
}