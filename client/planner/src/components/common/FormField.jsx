import Input from './Input.jsx';

const FormField = ({ type = 'text', ...props }) => {
    return (
        <div className="flex flex-col m-2">
            {props.label && (
                <label className="text-gray-700 font-bold mb-2">
                    {props.label}
                </label>
            )}
            <Input type={type} {...props} />
        </div>
    );
};

export default FormField;
