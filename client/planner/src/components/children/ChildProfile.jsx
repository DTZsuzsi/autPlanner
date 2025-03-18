// eslint-disable-next-line react/prop-types
function ChildProfile({child}){
    return(
      <div className="border-solid border-gray-200">
          {/* eslint-disable-next-line react/prop-types */}
          <p> Children first name: {child?.firstName}</p>
          {/* eslint-disable-next-line react/prop-types */}
          <p> Children last name: {child?.lastName}</p>
      </div>

    )
}

export default ChildProfile;