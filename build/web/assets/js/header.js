/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const siderClick = document.querySelector(".header__navigator i");

siderClick.onclick = function () {
    const sider = document.getElementsByTagName("sider")[0];
    const content = document.getElementsByTagName("content")[0];

    const siderWidth = getComputedStyle(sider).width;

    if (siderWidth === "250px") {
        sider.style.width = "0px";
        sider.style.position = "static";
        content.style.width = "100%";
        content.style.marginLeft = "0px";
    } else if (siderWidth === "0px") {
        sider.style.width = "250px";
        sider.style.position = "fixed";
        content.style.width = "calc(100% - 250px)";
        content.style.marginLeft = "250px";
    }
};