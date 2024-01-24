import React from 'react';
import { Modal, List } from 'antd';

function StatisticsPopup({ visible, handleClose, orders }) {
  // Calculate statistics
  let bookCount = 0;
  let totalPrice = 0;
  const bookTypeCount = {};

  orders.forEach((order) => {
    order.orderItems.forEach((item) => {
      bookCount += item.quantity;
      totalPrice += item.price;
      const bookType = item.book.type;
      if (bookType in bookTypeCount) {
        bookTypeCount[bookType] += item.quantity;
      } else {
        bookTypeCount[bookType] = item.quantity;
      }
    });
  });

  const data = Object.entries(bookTypeCount).map(([type, count]) => ({
    type,
    count,
  }));

  return (
    <Modal
      title="Order Statistics"
      visible={visible}
      onOk={handleClose}
      onCancel={handleClose}
      footer={null}
    >  
          <p>Total price: {totalPrice}</p>

      <p>Total number of books purchased: {bookCount}</p>
      <p>Number of book types purchased: {Object.keys(bookTypeCount).length}</p>
      <p>Books purchased by type:</p>
      <List
        itemLayout="horizontal"
        dataSource={data}
        renderItem={(item) => (
          <List.Item>
            <List.Item.Meta title={item.type} description={`${item.count} book(s)`} />
          </List.Item>
        )}
      />
    </Modal>
  );
}

export default StatisticsPopup;
