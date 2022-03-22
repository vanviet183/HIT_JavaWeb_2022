window.addEventListener('DOMContentLoaded', (event) => {
    var btnCoppyList = document.querySelectorAll('.btn-coppy');
    var shortLinkList = document.querySelectorAll('.short-link');
    var containTooltipList = document.querySelectorAll('.contain-tooltip');
    btnCoppyList.forEach((btnCoppy, index) => {
        btnCoppy.onclick = () => {
            console.log(shortLinkList[index])
            console.log(shortLinkList[index])
            shortLinkList[index].select();
            document.execCommand("copy");
            containTooltipList[index].classList.add("active");
            window.getSelection().removeAllRanges();
            setTimeout(function () {
                containTooltipList[index].classList.remove("active");
            }, 2000);
        }
    })


});