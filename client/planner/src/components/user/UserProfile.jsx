

// eslint-disable-next-line react/prop-types
function UserProfile({user}){

    return (
        <div className="border-solid border-gray-200">
            <p>
                First name: {user?.firstName}
            </p>
            <p>
                Last name: {user?.lastName}
            </p>
</div>
    )

}

export default UserProfile;