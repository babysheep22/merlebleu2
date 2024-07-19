

        // URL에서 검색어 파라미터 가져오기
        const urlParams = new URLSearchParams(window.location.search);
        const keyword = urlParams.get('keyword');

        // 검색어가 포함된 아이템만 표시
        const tableRows = document.querySelectorAll('#onepiece-results tbody tr');
        if (keyword) {
            tableRows.forEach(row => {
                const itemText = row.innerText.toLowerCase();
                if (!itemText.includes(keyword.toLowerCase())) {
                    row.style.display = 'none';
                }
            });
        }


         // const productsPerPage = 10; // 한 번에 보여줄 상품 수
 const productsPerPage = 10; // 한 번에 보여줄 상품 수
let currentIndex = 0;

function myFunction() {
    const hiddenRows = document.querySelectorAll('#onepiece-results tbody.hidden');
    const errorText = document.getElementById("error2");
    const btnText = document.getElementById("myBtn2");

    // 모든 히든 상품을 보여줌
    hiddenRows.forEach(row => {
        row.classList.remove('hidden');
    });

    // 에러 텍스트 표시
    errorText.style.display = 'block';
    // 버튼 비활성화
    btnText.disabled = true;

    // 새로운 상품이 로드될 때마다 전체 수량 업데이트
    updateTotalCount();
}

const sortOptions = document.getElementById('sortOptions');
const totalCount = document.getElementById('totalCount');

function getProductsFromHTML(includeHidden = false) {
    const tbodyElements = document.querySelectorAll('#onepiece-results tbody');
    const products = [];
    tbodyElements.forEach((element, index) => {
        const imgElement = element.querySelector('img');
        const overlayText = element.querySelector('.overlay-text').innerText;
        const outerText = element.querySelector('.outer.text-outer').innerText;
        const lineThroughText = element.querySelector('.line-through').innerText;
        const priceText = element.querySelector('.price').innerText;
        const discountText = element.querySelector('.discount').innerText;

        const product = {
            id: index,
            name: outerText,
            brand: overlayText,
            consumer_price: parseInt(lineThroughText.replace(/[^0-9]/g, ''), 10),
            sell_price: parseInt(priceText.replace(/[^0-9]/g, ''), 10),
            sale: parseInt(discountText.replace(/[^0-9]/g, ''), 10),
            image: imgElement.src,
            imageHover: imgElement.src.replace('.jpg', '2.jpg'), // 호버시 이미지
            rating: Math.random() * 5, // 예제 데이터를 위해 무작위로 설정
            sales: Math.floor(Math.random() * 1000), // 예제 데이터를 위해 무작위로 설정
            date: new Date().toISOString().split('T')[0], // 예제 데이터를 위해 오늘 날짜로 설정
            hidden: element.classList.contains('hidden') // 히든 여부 추가
        };
        if (includeHidden || !product.hidden) {
            products.push(product);
        }
    });
    return products;
}

function renderProducts(products) {
    const productContainer = document.querySelector('#onepiece-results');
    productContainer.innerHTML = '';
    products.forEach(product => {
        const productHTML = `
<tbody class="${product.hidden ? 'hidden' : ''}">
    <tr>
        <td>
            <div class="container-outer">
                <a href="#"><img src="${product.image}" width="345" height="345"
                        onmouseover="this.src='${product.imageHover}';"
                        onmouseout="this.src='${product.image}';">
                    <div class="overlay">
                        <p class="overlay-text" style="left: 50%; top: 30%;">${product.brand}</p>
                        <p class="outer text-outer"><a href="#">${product.name}</a></p>
                        <p class="line-through" style="text-decoration: line-through; color: #ccc; left: 10%;">${product.consumer_price.toLocaleString()}원</p>
                        <p class="price" style="left: 40%;">${product.sell_price.toLocaleString()}원</p>
                        <p class="discount" style="color: red; left: 70%;">${product.sale}%</p>
                        <div class="overlay2">
                            <a href="#"><p class="color-box"></p></a>
                            <a href="#"><p class="color-box2"></p></a>
                        </div>
                    </div>
                </a>
            </div>
        </td>
    </tr>
</tbody>`;
        productContainer.innerHTML += productHTML;
    });
    updateTotalCount();
}

function sortProducts(criteria) {
    const products = getProductsFromHTML(true); // 히든 상품 포함
    const sortedProducts = [...products];
    switch (criteria) {
        case 'recommended':
            sortedProducts.sort((a, b) => b.rating - a.rating);
            break;
        case 'newest':
            sortedProducts.sort((a, b) => new Date(b.date) - new Date(a.date));
            break;
        case 'price-low':
            sortedProducts.sort((a, b) => a.sell_price - b.sell_price);
            break;
        case 'price-high':
            sortedProducts.sort((a, b) => b.sell_price - a.sell_price);
            break;
        case 'name':
            sortedProducts.sort((a, b) => a.name.localeCompare(b.name));
            break;
        default:
            break;
    }
    renderProducts(sortedProducts);
}

sortOptions.addEventListener('change', (event) => {
    const criteria = event.target.value;
    sortProducts(criteria);
});

document.addEventListener('DOMContentLoaded', () => {
    // 처음 로드할 때 전체 수량 업데이트
    updateTotalCount();
});

function updateTotalCount() {
    const products = getProductsFromHTML(true); // 히든 상품 포함
    totalCount.innerHTML = products.length;
}

// 처음 로드할 때 전체 수량 업데이트
updateTotalCount();