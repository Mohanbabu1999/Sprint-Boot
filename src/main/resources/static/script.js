document.addEventListener('DOMContentLoaded', function() {
    // Login page animation
    const loginContainer = document.querySelector('.login-container');
    if (loginContainer) {
        setTimeout(() => {
            loginContainer.style.opacity = '1';
            loginContainer.style.transform = 'translateY(0)';
        }, 100);
    }
    
    // Menu page functionality
    const orderButtons = document.querySelectorAll('.order-btn');
    orderButtons.forEach(button => {
        button.addEventListener('click', function() {
            const foodItem = this.closest('.food-item');
            const itemName = foodItem.querySelector('h3').textContent;
            const quantity = foodItem.querySelector('.quantity-select').value;
            alert(`Order placed for ${quantity} ${itemName}(s)!`);
        });
    });
});
