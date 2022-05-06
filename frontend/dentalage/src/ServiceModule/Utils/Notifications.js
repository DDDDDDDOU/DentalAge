/*
writer : DJW
date   : 2021/6/01
 **/

import { Button, notification, Space } from 'antd';

export const primaryNotification = (message,description,duration=0) => {
    notification.open({
        message:message,
        description:
            description,
        duration:duration,
        onClick: () => {
            console.log('Notification Clicked!');
        },
    });
};

export const successNotification = (message,description,duration=0) => {
    notification["success"]({
        message:message,
        description: description,
        duration:duration
    });
};
export const infoNotification = (message,description,duration=0) => {
    notification["info"]({
        message:message,
        description: description,
        duration:duration
    });
};
export const warningNotification = (message,description,duration=0) => {
    notification["warning"]({
        message:message,
        description: description,
        duration:duration
    });
};

export const errorNotification = (message,description,duration=0) => {
    notification["error"]({
        message:message,
        description: description,
        duration:duration
    });
};