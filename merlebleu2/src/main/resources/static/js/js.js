function redirectToSearch1() {
    const searchKeyword = document.getElementById('searchKeyword1').value.trim();
    let query = '';

    // Determine the search query
    if (searchKeyword === '아우터') {
        query = 'search_outer#';
    } else if (searchKeyword === '탑') {
        query = 'search_top#';
    } else if (searchKeyword === '스커트') {
        query = 'search_skirt#';
    } else if (searchKeyword === '팬츠') {
        query = 'search_pants#';
    } else if (searchKeyword === '원피스') {
        query = 'search_onepiece#';
    } else if (searchKeyword === '패션잡화') {
        query = 'search_stuff#';
    } else {
        // Show error message
        document.getElementById('errorMessage1').textContent = '검색어가 카테고리에 없습니다. 카테고리 중 하나를 선택해주세요.';
        return;
    }

    // Redirect to the search results page
    window.location.href = query;
}

function handleSearch() {
    const searchKeyword = document.getElementById('searchKeyword2').value.trim();
    let query = '';

    // Show error message and return if search keyword is empty
    if (!searchKeyword) {
        document.getElementById('errorMessage2').textContent = ''; // 에러 메시지 숨기기
        return;
    }

    // Determine the search query
    if (searchKeyword === '아우터') {
        query = 'search_outer#';
    } else if (searchKeyword === '탑') {
        query = 'search_top#';
    } else if (searchKeyword === '스커트') {
        query = 'search_skirt#';
    } else if (searchKeyword === '팬츠') {
        query = 'search_pants#';
    } else if (searchKeyword === '원피스') {
        query = 'search_onepiece#';
    } else if (searchKeyword === '패션잡화') {
        query = 'search_stuff#';
    } else {
        // Show error message and return if search keyword is not in categories
        document.getElementById('errorMessage2').textContent = '검색어가 카테고리에 없습니다. 카테고리 중 하나를 선택해주세요.';
        return;
    }

    // Hide error message if search keyword is valid
    document.getElementById('errorMessage2').textContent = '';

    // Save the search keyword to the recent searches and redirect to search results page
    saveAndRedirect(searchKeyword, query);
}

document.getElementById('searchKeyword1').addEventListener('input', function () {
    const errorMessage = document.getElementById('errorMessage1');
    const searchKeyword = this.value.trim();

    // Hide error message if the input field is empty
    if (!searchKeyword) {
        errorMessage.textContent = '';
    }
});

document.getElementById('searchKeyword2').addEventListener('input', function () {
    const errorMessage = document.getElementById('errorMessage2');
    const searchKeyword = this.value.trim();

    // Hide error message if the input field is empty
    if (!searchKeyword) {
        errorMessage.textContent = '';
    }
});
// Function to save the search keyword to recent searches and redirect to the search results page
function saveAndRedirect(searchKeyword, query) {
    // Get the recent searches from cookies or initialize an empty array
    const recentSearches = getCookie('recentSearches') ? JSON.parse(getCookie('recentSearches')) : [];

    // Add the current search keyword to the beginning of the recent searches array
    recentSearches.unshift(searchKeyword);

    // Keep only the latest 5 searches
    const uniqueRecentSearches = [...new Set(recentSearches)].slice(0, 5);

    // Update the recent searches in the cookies
    setCookie('recentSearches', JSON.stringify(uniqueRecentSearches));

    // Redirect to the search results page
    window.location.href = query;
}

// Function to set a cookie with the given name and value
function setCookie(name, value) {
    document.cookie = `${name}=${value}; path=/`;
}

// Function to get the value of a cookie with the given name
function getCookie(name) {
    const cookieValue = document.cookie.match(`(^|;)\\s*${name}\\s*=\\s*([^;]+)`);
    return cookieValue ? cookieValue.pop() : null;
}

// Function to delete a cookie with the given name
function deleteCookie(name) {
    document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
}

// Function to delete all recent searches
function allDeleteCookie() {
    deleteCookie('recentSearches');
    updateRecentSearchList([]);
}

// Load the recent searches when the page loads
window.onload = function () {
    const recentSearches = getCookie('recentSearches') ? JSON.parse(getCookie('recentSearches')) : [];
    updateRecentSearchList(recentSearches);
};

// Function to update the recent search list in the HTML
function updateRecentSearchList(recentSearches) {
    const recentSearchList = document.getElementById('recentSearchList');
    recentSearchList.innerHTML = ''; // Clear the existing list

    if (recentSearches.length === 0) {
        const listItem = document.createElement('li');
        listItem.textContent = '최근 검색어가 없습니다.';
        recentSearchList.appendChild(listItem);
    } else {
        recentSearches.forEach((search, index) => {
            const listItem = document.createElement('li');
            const link = document.createElement('a');
            link.href = `/shop/search_result.php?search_str=${encodeURIComponent(search)}&x=0&y=0`;
            link.innerHTML = `<p>${search}</p>`;
            listItem.appendChild(link);

            const deleteButton = document.createElement('a');
            deleteButton.className = 'del_btn';
            deleteButton.textContent = 'X';
            deleteButton.onclick = function () {
                deleteRecentSearch(index);
            };
            listItem.appendChild(deleteButton);
            recentSearchList.appendChild(listItem);
        });
    }
}




/*========================================================================================================*/

let slideIndex = 1;
let slideTimer;

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("mySlides");
    let dots = document.getElementsByClassName("dot");

    if (n > slides.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = slides.length }

    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";
}

function plusSlides(n) {
    clearTimeout(slideTimer);
    showSlides(slideIndex += n);
    slideTimer = setTimeout(autoShowSlides, 2000); // Restart automatic sliding
}

function currentSlide(n) {
    clearTimeout(slideTimer);
    showSlides(slideIndex = n);
    slideTimer = setTimeout(autoShowSlides, 2000); // Restart automatic sliding
}

function autoShowSlides() {
    slideIndex++;
    showSlides(slideIndex);
    slideTimer = setTimeout(autoShowSlides, 3200); // Change image every 2 seconds
}

// Initial call
showSlides(slideIndex);
slideTimer = setTimeout(autoShowSlides, 5000);

// 이미지에 대한 요소 가져오기
let slides = document.getElementsByClassName("mySlides");

// // 각 이미지에 대해 이벤트 리스너 추가
Array.from(slides).forEach((slide) => {
    // 이미지에 마우스가 들어왔을 때 실행될 함수
    slide.addEventListener("mouseenter", function () {
        clearTimeout(slideTimer); // 슬라이드 타이머를 멈춤
    });

    // 이미지에서 마우스가 나갔을 때 실행될 함수
    slide.addEventListener("mouseleave", function () {
        slideTimer = setTimeout(autoShowSlides, 2000); // 슬라이드 타이머를 재시작
    });
});



function hideSlides() {
    let slides = document.getElementsByClassName("mySlides");
    for (let i = 0; i < slides.length; i++) {
        slides[i].classList.add("fade"); // 이미지가 변경되기 전에 페이드 아웃 효과 추가
    }
}


/*========================================================================================================*/

// 페이지가 스크롤될 때 실행되는 함수
window.addEventListener('scroll', function () {
    var scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    var headerLogo = document.querySelector('.header_logo');
    var headerBottomNav = document.querySelector('.header_bottom_nav');

    if (scrollTop > 50) {
        headerLogo.style.opacity = '0';
        headerLogo.style.top = '-10px';
        headerBottomNav.style.top = '40px'; // Make it stick to the top
        headerBottomNav.style.backgroundColor = 'white'; // Change background color to white
    } else {
        headerLogo.style.opacity = '1';
        headerLogo.style.top = '70px';
        headerBottomNav.style.top = '150px'; // Adjust this value as needed
        headerBottomNav.style.backgroundColor = ''; // Reset to default background color
    }
});

/*========================================================================================================*/

function cateHover(className) {
    var element = document.querySelector('.' + className);
    element.classList.add('visible');
}

function cateOut() {
    var element = document.querySelector('.cate_box_child0');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child1');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child2');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child3');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child4');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child5');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child6');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child7');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child8');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child9');
    element.classList.remove('visible');
    var element = document.querySelector('.cate_box_child10');
    element.classList.remove('visible');
}

/*========================================================================================================*/

// history.scrollRestoration = "manual";
//새로고침 시 항상 top:0 위치로 이동된다.



/*==================================================================================================*/

const scrollContainer = document.querySelector("[data-scroll-content]");
const scrollIndicator = document.querySelector("[data-scroll-indicator]");

let scrollPosition = 0;
const scrollAmount = 400;
const verticalScrollSpeed = 100; // Adjust vertical scroll speed
const horizontalScrollSpeed = 105; // Adjust horizontal scroll speed
let autoScrollTimer;

function autoScroll() {
    scrollPosition += scrollAmount;
    scrollContainer.scrollTo({
        left: scrollPosition,
        behavior: 'false' // Apply smooth scrolling
    });

    if (scrollPosition >= scrollContainer.scrollWidth - scrollContainer.clientWidth) {
        scrollPosition = 0;
    }
    updateScrollIndicator();
}

function startAutoScroll() {
    autoScrollTimer = setInterval(autoScroll, 3000); // Run every 3 seconds
}

function stopAutoScroll() {
    clearInterval(autoScrollTimer);
}

// Page load: start automatic scrolling
startAutoScroll();

// Update scroll indicator function
function updateScrollIndicator() {
    const maxScrollLeft = scrollContainer.scrollWidth - scrollContainer.clientWidth;
    const scrollLeft = scrollContainer.scrollLeft;
    const scrollPercentage = (scrollLeft / maxScrollLeft) * 100;
    scrollIndicator.style.width = scrollPercentage + "%";

    // Map the scroll percentage to 75% of the indicator container
    const indicatorWidthPercentage = (scrollPercentage * 73) / 100;
    scrollIndicator.style.width = indicatorWidthPercentage + "%";
}

// 마우스 휠 이벤트 처리
scrollContainer.addEventListener('wheel', function (event) {
    const delta = Math.sign(event.deltaY);
    const maxScrollLeft = scrollContainer.scrollWidth - scrollContainer.clientWidth;

    if (scrollContainer.scrollLeft >= maxScrollLeft && delta > 0) {
        window.scrollBy({
            top: delta * verticalScrollSpeed,
            behavior: 'false'
        });
    } else if (scrollContainer.scrollLeft === 0 && delta < 0) {
        window.scrollBy({
            top: delta * verticalScrollSpeed,
            behavior: 'false'
        });
    } else {
        scrollContainer.scrollLeft += delta * horizontalScrollSpeed; // Adjust horizontal scroll speed
    }
    event.preventDefault();
    updateScrollIndicator();
});

// 스크롤 이벤트 발생 시 게이지 바 업데이트
scrollContainer.addEventListener('scroll', updateScrollIndicator);

function autoScroll() {
    const maxScrollLeft = scrollContainer.scrollWidth - scrollContainer.clientWidth;
    if (scrollContainer.scrollLeft >= maxScrollLeft) {
        scrollContainer.scrollTo({
            left: 0,
            behavior: 'smooth'
        });
    } else {
        scrollContainer.scrollBy({
            left: scrollAmount,
            behavior: 'smooth'
        });
    }
    updateScrollIndicator();
}


/*=======================================================================================*/

// 두 번째 슬라이드 영역
// 첫 번째 슬라이드 영역
const scrollContainer2 = document.querySelector("[data-scroll-content2]");
const scrollIndicator2 = document.querySelector("[data-scroll-indicator2]");
let scrollPosition2 = 0;
const scrollAmount2 = 400;
const verticalScrollSpeed2 = 100;
const horizontalScrollSpeed2 = 105;
let autoScrollTimer2;

function autoScroll2() {
    scrollPosition2 += scrollAmount2;
    scrollContainer2.scrollTo({
        left: scrollPosition2,
        behavior: 'false'
    });

    if (scrollPosition2 >= scrollContainer2.scrollWidth - scrollContainer2.clientWidth) {
        scrollPosition2 = 0;
        setTimeout(() => {
            scrollContainer2.scrollTo({
                left: 0,
                behavior: 'false'
            });
        }, 500); // Adjust the delay to ensure smooth transition
    }
    updateScrollIndicator2();
}

function startAutoScroll2() {
    autoScrollTimer2 = setInterval(autoScroll2, 3000);
}

function stopAutoScroll2() {
    clearInterval(autoScrollTimer2);
}

// Page load: start automatic scrolling
startAutoScroll2();

function updateScrollIndicator2() {
    const maxScrollLeft2 = scrollContainer2.scrollWidth - scrollContainer2.clientWidth;
    const scrollLeft2 = scrollContainer2.scrollLeft;
    const scrollPercentage2 = (scrollLeft2 / maxScrollLeft2) * 100;
    const indicatorWidthPercentage2 = (scrollPercentage2 * 73) / 100;
    scrollIndicator2.style.width = indicatorWidthPercentage2 + "%";
}

scrollContainer2.addEventListener('wheel', function (event) {
    const delta2 = Math.sign(event.deltaY);
    const maxScrollLeft2 = scrollContainer2.scrollWidth - scrollContainer2.clientWidth;

    if (scrollContainer2.scrollLeft >= maxScrollLeft2 && delta2 > 0) {
        window.scrollBy({
            top: delta2 * verticalScrollSpeed2,
            behavior: 'false'
        });
    } else if (scrollContainer2.scrollLeft === 0 && delta2 < 0) {
        window.scrollBy({
            top: delta2 * verticalScrollSpeed2,
            behavior: 'false'
        });
    } else {
        scrollContainer2.scrollLeft += delta2 * horizontalScrollSpeed2;
    }
    event.preventDefault();
    updateScrollIndicator2();
});

scrollContainer2.addEventListener('scroll', updateScrollIndicator2);

function autoScroll2() {
    const maxScrollLeft2 = scrollContainer2.scrollWidth - scrollContainer2.clientWidth;
    if (scrollContainer2.scrollLeft >= maxScrollLeft2) {
        scrollContainer2.scrollTo({
            left: 0,
            behavior: 'smooth'
        });
    } else {
        scrollContainer2.scrollBy({
            left: scrollAmount2,
            behavior: 'smooth'
        });
    }
    updateScrollIndicator2();
}
// // Function to set a cookie with the given name and value
// function setCookie(name, value) {
//     document.cookie = `${name}=${value}; path=/`;
// }

// // Function to get the value of a cookie with the given name
// function getCookie(name) {
//     const cookieValue = document.cookie.match(`(^|;)\\s*${name}\\s*=\\s*([^;]+)`);
//     return cookieValue ? cookieValue.pop() : null;
// }

// // Function to delete a cookie with the given name
// function deleteCookie(name) {
//     document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
// }

// // Function to handle search and update recent searches
// function handleSearch() {
//     const keyword = document.querySelector('.searchKeyword2').value.trim();
//     if (keyword !== '') {
//         const recentSearches = getCookie('recentSearches') ? JSON.parse(getCookie('recentSearches')) : [];
//         recentSearches.unshift(keyword); // Add the latest search at the beginning
//         setCookie('recentSearches', JSON.stringify(recentSearches.slice(0, 5))); // Keep only the latest 5 searches
//         updateRecentSearchList(recentSearches);
//         window.location.href = `/shop/search_result.php?search_str=${encodeURIComponent(keyword)}&x=0&y=0`;
//     } else {
//         alert('검색어를 입력하세요.');
//     }
// }

// // Function to update the recent search list in the HTML
// function updateRecentSearchList(recentSearches) {
//     const recentSearchList = document.getElementById('recentSearchList');
//     recentSearchList.innerHTML = ''; // Clear the existing list
//     if (recentSearches.length === 0) {
//         const listItem = document.createElement('li');
//         listItem.textContent = '최근 검색어가 없습니다.';
//         recentSearchList.appendChild(listItem);
//     } else {
//         recentSearches.forEach((search, index) => {
//             const listItem = document.createElement('li');
//             const link = document.createElement('a');
//             link.href = `/shop/search_result.php?search_str=${encodeURIComponent(search)}&x=0&y=0`;
//             link.innerHTML = `<p>${search}</p>`;
//             listItem.appendChild(link);
//             const deleteButton = document.createElement('a');
//             deleteButton.className = 'del_btn';
//             deleteButton.textContent = 'X';
//             deleteButton.onclick = function () {
//                 deleteRecentSearch(index);
//             };
//             listItem.appendChild(deleteButton);
//             recentSearchList.appendChild(listItem);
//         });
//     }
// }

// Function to delete a recent search by index
function deleteRecentSearch(index) {
    const recentSearches = getCookie('recentSearches') ? JSON.parse(getCookie('recentSearches')) : [];
    recentSearches.splice(index, 1); // Remove the search at the specified index
    setCookie('recentSearches', JSON.stringify(recentSearches)); // Update the cookie
    updateRecentSearchList(recentSearches); // Update the recent search list
}

// Function to delete all recent searches
function allDeleteCookie() {
    deleteCookie('recentSearches');
    updateRecentSearchList([]);
}

// Load the recent searches when the page loads
window.onload = function () {
    const recentSearches = getCookie('recentSearches') ? JSON.parse(getCookie('recentSearches')) : [];
    updateRecentSearchList(recentSearches);
};